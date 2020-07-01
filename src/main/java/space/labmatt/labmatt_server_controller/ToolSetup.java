package space.labmatt.labmatt_server_controller;

import Mangers.ColourConvert;
import Tools.AdvancedMechanics.RegisterAdvancedMechanics;
import Tools.ChatColours.RegisterChatColours;
import Tools.CraftExtra.RegisterCraftCraftExtra;
import Tools.CustomGameRule.RegisterCustomGameRules;
import Tools.Hammer.RegisterHammer;
import Tools.Home.RegisterHomeTool;
import Tools.InvShulker.RegisterInvShulker;
import Tools.InventorySnooper.RegisterInventorySnooper;
import Tools.LMSCWorldManger.RegisterLMSCWorldManger;
import Tools.LMSC_Personalisation.Register_LMSC_Rersonalisation;
import Tools.LandGuard.RegisterLandGuard;
import Tools.Lock.Register_Lock_Tool;
import Tools.LogStrip.Block_Prevent_Log_Strip;
import Tools.PetProtec.RegisterPetProtec;
import Tools.Signage.RegisterSignage;
import Tools.TreeChop.RegisterTreeChop;

class ToolSetup {

    private LABMATT_SERVER_CONTROLLER plugin;

    ToolSetup(LABMATT_SERVER_CONTROLLER labmatt_server_controller) {
        this.plugin = labmatt_server_controller;
    }

    //Registers all LMSC tools for usage.
    void toolReg() {
        plugin.reloadConfig();
        Register_Lock_Tool lockreg = new Register_Lock_Tool(plugin);
        RegisterHomeTool homereg = new RegisterHomeTool(plugin);
        RegisterHammer hammer = new RegisterHammer(plugin);
        Register_LMSC_Rersonalisation per = new Register_LMSC_Rersonalisation(plugin);
        RegisterChatColours chColour = new RegisterChatColours(plugin);
        RegisterPetProtec petprotec = new RegisterPetProtec(plugin);
        RegisterLandGuard landGuard = new RegisterLandGuard(plugin);
        RegisterInventorySnooper snoop = new RegisterInventorySnooper(plugin);
        RegisterCraftCraftExtra craftExtra = new RegisterCraftCraftExtra(plugin);
        RegisterTreeChop treeChop = new RegisterTreeChop(plugin);
        RegisterCustomGameRules customGameRules = new RegisterCustomGameRules(plugin);
        RegisterInvShulker registerInvShulker = new RegisterInvShulker(plugin);
        ColourConvert colourConvert = new ColourConvert();
        RegisterLMSCWorldManger registerLMSCWorldManger = new RegisterLMSCWorldManger(plugin);
        RegisterSignage signage = new RegisterSignage(plugin);

        colourConvert.setHash();
        hammer.registerHammer();
        lockreg.registerLock();
        homereg.registerHome();
        per.regPersonalisation();
        petprotec.registerPetProtec();
        landGuard.regLandGuard();
        snoop.registerInventorySnooper();
        treeChop.registerTreeChop();
        craftExtra.registerCraftExtra();
        customGameRules.registerCustomGameRules();
        registerInvShulker.registerShulkerInv();
        chColour.regChatcolour();
        registerLMSCWorldManger.registerLMSCWorldManger();
        signage.registerSignage();
        new RegisterAdvancedMechanics(plugin);

        plugin.getServer().getPluginManager().registerEvents(new Block_Prevent_Log_Strip(plugin), plugin);

    }
}
