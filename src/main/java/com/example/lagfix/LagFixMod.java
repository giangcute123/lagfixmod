package com.example.lagfix;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LagFixMod implements ModInitializer {

    public static final String MOD_ID = "lagfix";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("[LagFix] Mod đã khởi động - throttle AI mob ở xa: NEAR={}  FAR={}",
                LagFixConfig.NEAR_RANGE, LagFixConfig.FAR_RANGE);
    }
}
