package com.gibbdev.server.display;

import com.gibbdev.SculkAlarm;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class RecoveryCompassAction implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.isCancelled()) {return;}
        Player p = e.getPlayer();

        if (p.getInventory().getItemInMainHand().getType().equals(Material.RECOVERY_COMPASS)
                && p.hasPermission("sculk_alarm.toggle")
                && e.getAction().isRightClick()
                && e.getHand().equals(EquipmentSlot.HAND)) {
            PersistentDataContainer pData = p.getPersistentDataContainer();
            if (!(pData.has(SculkAlarm.dataToggle))) { pData.set(SculkAlarm.dataToggle, PersistentDataType.INTEGER, 0);}
            else if (pData.get(SculkAlarm.dataToggle, PersistentDataType.INTEGER) == 1) {
                if (!(pData.has(SculkAlarm.dataAppearance))) {
                    pData.set(SculkAlarm.dataAppearance, PersistentDataType.STRING, "chat");
                    ChatGenerator.sendAlarmChat(p);
                }
                else if (pData.get(SculkAlarm.dataAppearance, PersistentDataType.STRING).equals("chat")) { ChatGenerator.sendAlarmChat(p);}
                else if (pData.get(SculkAlarm.dataAppearance, PersistentDataType.STRING).equals("sidebar")) { SidebarGenerator.toggleSidebar(p);}
            }
        }
    }
}
