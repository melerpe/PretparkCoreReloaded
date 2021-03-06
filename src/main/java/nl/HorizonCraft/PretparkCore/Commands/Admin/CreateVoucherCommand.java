/*
 * Copyright (c) 2015-2016 Tim Medema
 *
 * This plugin has no licence on it. But that DOESN'T mean you can use it.
 * See the COPYRIGHT.txt for in the root for more information.
 *
 * You are allowed to:
 * - Read the code, and use it for educational purposes.
 * - Ask me questions about how this plugin works and what some of the components do.
 *
 * You are NOT allowed to:
 * - Use it without my explicit permission.
 */

package nl.HorizonCraft.PretparkCore.Commands.Admin;

import nl.HorizonCraft.PretparkCore.Utilities.ChatUtils;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import nl.HorizonCraft.PretparkCore.Utilities.Objects.Voucher;
import nl.HorizonCraft.PretparkCore.Utilities.Variables;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

/**
 * Created by Cooltimmetje on 12/8/2015 at 6:18 PM.
 */
public class CreateVoucherCommand implements CommandExecutor {

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static Random rnd = new Random();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if(cmd.getLabel().equals("createvoucher")){
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.getName().equals("Jordy010NL") || p.getName().equals("Cooltimmetje")) {
                    if(args[0].equals("-list")){
                        for(Voucher voucher : Variables.vouchers){
                            ChatUtils.sendMsg(p, "&aCode: " + voucher.getCode());
                            ChatUtils.sendMsg(p, "&6Coins: " + voucher.getCoins());
                            ChatUtils.sendMsg(p, "&9EXP: " + voucher.getExp());
                            ChatUtils.sendMsg(p, "&3Boxes: " + voucher.getBoxes());
                            ChatUtils.sendMsg(p, "&dKeys: " + voucher.getKeys());
                            ChatUtils.sendMsg(p, "&aAantal: " + voucher.getUses_left());
                            p.sendMessage(" ");
                        }
                    } else {
                        if (args.length >= 5) {
                            if (MiscUtils.isInt(args[0]) && MiscUtils.isInt(args[1]) && MiscUtils.isInt(args[2]) && MiscUtils.isInt(args[3]) && MiscUtils.isInt(args[4])) {
                                String code = randomString(6);

                                if((args.length >= 6)) {
                                    if(args[5].length() <= 50){
                                        code = args[5].toUpperCase();
                                    } else {
                                        ChatUtils.sendMsgTag(p, "Vouchers", ChatUtils.error + "De code mag maximaal 50 karakters zijn.");
                                    }
                                }

                                if (MiscUtils.getVoucher(code) == null) {
                                    Voucher voucher = new Voucher(code, Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
                                    ChatUtils.sendMsg(p, "&8&l--- &a&lVOUCHER AANGEMAAKT &8&l---");
                                    ChatUtils.sendMsg(p, "&aCode: " + voucher.getCode());
                                    ChatUtils.sendMsg(p, "&6Coins: " + voucher.getCoins());
                                    ChatUtils.sendMsg(p, "&9EXP: " + voucher.getExp());
                                    ChatUtils.sendMsg(p, "&3Boxes: " + voucher.getBoxes());
                                    ChatUtils.sendMsg(p, "&dKeys: " + voucher.getKeys());
                                    ChatUtils.sendMsg(p, "&aAantal: " + voucher.getUses_left());
                                } else {
                                    ChatUtils.sendMsgTag(p, "Vouchers", ChatUtils.error + "Er ging iets mis, probeer het nog eens.");
                                }
                            } else {
                                ChatUtils.sendMsgTag(p, "Vouchers", ChatUtils.error + "Gebruik: /createvoucher <coins> <exp> <boxes> <keys> <uses> [code]");
                            }
                        } else {
                            ChatUtils.sendMsgTag(p, "Vouchers", ChatUtils.error + "Gebruik: /createvoucher <coins> <exp> <boxes> <keys> <uses> [code]");
                        }
                    }
                } else {
                    ChatUtils.sendNoPremTag(p, "Vouchers");
                }
            }
        }
        return true;
    }

    private static String randomString( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }


}
