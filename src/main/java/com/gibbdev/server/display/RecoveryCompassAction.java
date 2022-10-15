package com.gibbdev.server.display;

import com.gibbdev.SculkAlram;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
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
        Player p = e.getPlayer();

        if (p.getInventory().getItemInMainHand().getType().equals(Material.RECOVERY_COMPASS)
                && p.hasPermission("sculk_alarm.toggle")
                && e.getAction().isRightClick()
                && e.getHand().equals(EquipmentSlot.HAND)) {
            PersistentDataContainer pData = p.getPersistentDataContainer();
            NamespacedKey dataToggle =  new NamespacedKey(SculkAlram.getPlugin(), "sa_toggle");
            NamespacedKey dataAppearance =  new NamespacedKey(SculkAlram.getPlugin(), "sa_appearance");
            if (!(pData.has(dataToggle))) { pData.set(dataToggle, PersistentDataType.INTEGER, 0);}
            else if (pData.get(dataToggle, PersistentDataType.INTEGER) == 1) {
                if (!(pData.has(dataAppearance))) {
                    pData.set(dataAppearance, PersistentDataType.STRING, "chat");
                    ChatGenerator.sendAlarmChat(p);
                }
                else if (pData.get(dataAppearance, PersistentDataType.STRING).equals("chat")) { ChatGenerator.sendAlarmChat(p);}
                else if (pData.get(dataAppearance, PersistentDataType.STRING).equals("sidebar")) { SidebarGenerator.toggleSidebar(p);}
            }
        }
    }
}
