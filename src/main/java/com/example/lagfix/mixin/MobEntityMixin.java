package com.example.lagfix.mixin;

import com.example.lagfix.LagFixConfig;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEntity.class)
public class MobEntityMixin {

    @Inject(method = "mobTick", at = @At("HEAD"), cancellable = true)
    private void lagfix$throttleAiTick(ServerWorld world, CallbackInfo ci) {
        MobEntity self = (MobEntity) (Object) this;

        if (LagFixConfig.SKIP_IF_HAS_TARGET && self.getTarget() != null) {
            return;
        }

        PlayerEntity nearest = world.getClosestPlayer(self, LagFixConfig.FAR_RANGE);

        int interval;
        if (nearest == null) {
            interval = LagFixConfig.FAR_THROTTLE_INTERVAL;
        } else {
            double dist = self.distanceTo(nearest);
            if (dist <= LagFixConfig.NEAR_RANGE) {
                interval = 1;
            } else if (dist <= LagFixConfig.FAR_RANGE) {
                interval = LagFixConfig.MEDIUM_THROTTLE_INTERVAL;
            } else {
                interval = LagFixConfig.FAR_THROTTLE_INTERVAL;
            }
        }

        if (interval <= 1) {
            return;
        }

        int age = self.age;
        boolean shouldTickThisFrame = ((age + self.getId()) % interval) == 0;

        if (!shouldTickThisFrame) {
            ci.cancel();
        }
    }
}
