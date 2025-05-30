package de.lost.vortex.bulletTimeMod;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.attribute.EntityAttributes;
import de.lost.vortex.bulletTimeMod.sound.ModSound;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class TickHandler {
    // RICHTIG: SoundEvents mit Identifier.of()
    public static final SoundEvent BULLET_TIME_ENTER = SoundEvent.of(Identifier.of("bullet_time_mod:bullet_time_enter"));
    public static final SoundEvent BULLET_TIME_LEAVE = SoundEvent.of(Identifier.of("bullet_time_mod:bullet_time_leave"));

    // Status pro Spieler speichern
    private static final Map<UUID, Boolean> bulletTimeActive = new HashMap<>();
    private static final Map<UUID, Integer> bulletTimeTicks = new HashMap<>();

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerWorld world : server.getWorlds()) {
                for (ServerPlayerEntity player : world.getPlayers()) {

                    boolean isFalling = !player.isOnGround() && player.getVelocity().y < 0;
                    ItemStack stack = player.getMainHandStack();
                    boolean hasBow = stack.getItem() == Items.BOW;
                    boolean isChargingBow = hasBow &&
                            player.getItemUseTimeLeft() < stack.getItem().getMaxUseTime(stack, player);
                    boolean fallingLongEnough = player.fallDistance > 1.6;

                    double radius = 20.0;
                    UUID playerId = player.getUuid();

                    boolean currentlyActive = isFalling && isChargingBow && fallingLongEnough;
                    boolean wasActive = bulletTimeActive.getOrDefault(playerId, false);

                    // Bullet Time startet
                    if (currentlyActive && !wasActive) {
                        bulletTimeActive.put(playerId, true);
                        bulletTimeTicks.put(playerId, 0);

                        // Sound: Erster Teil (3s)
                        player.playSound(BULLET_TIME_ENTER, 1.0f, 1.0f);
                        world.playSound(
                                null,
                                player.getX(), player.getY(), player.getZ(),
                                BULLET_TIME_ENTER,
                                SoundCategory.PLAYERS,
                                1.0f, 1.0f
                        );
                    }

                    // Bullet Time läuft
                    if (currentlyActive) {
                        int ticks = bulletTimeTicks.getOrDefault(playerId, 0) + 1;
                        bulletTimeTicks.put(playerId, ticks);

                        // Spieler langsamer fallen lassen
                        Vec3d velocity = player.getVelocity();
                        Vec3d newVelocity = new Vec3d(velocity.x, velocity.y * 0.2, velocity.z);
                        player.setVelocity(newVelocity);
                        player.velocityModified = true;

                        // Andere Spieler im Radius verlangsamen
                        for (ServerPlayerEntity other : world.getPlayers()) {
                            if (other == player) continue;
                            if (other.squaredDistanceTo(player) > radius * radius) continue;

                            EntityAttributeInstance speedAttr = other.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
                            if (speedAttr != null) {
                                double baseSpeed = 0.1;
                                double slowedSpeed = baseSpeed * 0.75;
                                if (Math.abs(speedAttr.getBaseValue() - slowedSpeed) > 0.001) {
                                    speedAttr.setBaseValue(slowedSpeed);
                                }
                            }
                        }

                        // Mobs verlangsamen
                        for (LivingEntity mob : world.getEntitiesByClass(
                                LivingEntity.class,
                                player.getBoundingBox().expand(radius),
                                entity -> !(entity instanceof ServerPlayerEntity))) {

                            mob.addStatusEffect(new StatusEffectInstance(
                                    StatusEffects.SLOWNESS,
                                    2,
                                    2,
                                    false,
                                    false
                            ));

                            Vec3d mobVel = mob.getVelocity();
                            if (!mob.isOnGround() && mobVel.y < 0) {
                                Vec3d slowedMobVel = new Vec3d(mobVel.x, mobVel.y * 0.2, mobVel.z);
                                mob.setVelocity(slowedMobVel);
                                mob.velocityModified = true;
                            }
                        }
                    }

                    // Bullet Time endet
                    if (!currentlyActive && wasActive) {
                        bulletTimeActive.put(playerId, false);
                        bulletTimeTicks.remove(playerId);

                        // Sound: Restlicher Teil (3s)
                        world.playSound(
                                null,
                                player.getX(), player.getY(), player.getZ(),
                                BULLET_TIME_LEAVE,
                                SoundCategory.PLAYERS,
                                1.0f, 1.0f
                        );

                        player.playSound(BULLET_TIME_LEAVE, 1.0f, 1.0f);
                        world.playSound(
                                null,
                                player.getX(), player.getY(), player.getZ(),
                                BULLET_TIME_LEAVE,
                                SoundCategory.PLAYERS,
                                1.0f, 1.0f
                        );


                        // Andere Spieler im Radius zurücksetzen
                        for (ServerPlayerEntity other : world.getPlayers()) {
                            if (other == player) continue;
                            if (other.squaredDistanceTo(player) > radius * radius) continue;
                            EntityAttributeInstance speedAttr = other.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
                            if (speedAttr != null) {
                                double baseSpeed = 0.1;
                                if (Math.abs(speedAttr.getBaseValue() - baseSpeed) > 0.001) {
                                    speedAttr.setBaseValue(baseSpeed);
                                }
                            }
                        }
                    }
                }
            }
        });
    }
}
