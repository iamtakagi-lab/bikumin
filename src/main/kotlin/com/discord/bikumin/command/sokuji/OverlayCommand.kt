package com.discord.bikumin.command.sokuji

import com.discord.bikumin.Config
import com.discord.bikumin.manager.SokujiManager
import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.EmbedBuilder

class OverlayCommand: Command() {

    init {
        this.name = "overlay"
        this.help = "配信用OverlayURL"
    }

    override fun execute(event: CommandEvent?) {
        event?.apply {
            val sokuji = SokujiManager.getSokuji(guild.idLong, channel.idLong)?: return reply("即時集計は開始されていません")
            sokuji.apply {
                reply(EmbedBuilder().apply {
                    setColor(Config.EMBED_COLOR)
                    setTitle("$teamA vs $teamB")
                    addField("Broadcast Overlay", getOverlayUrl(), false)
                }.build())
            }
        }
    }
}