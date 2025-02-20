package com.github.quiltservertools.ledger.network.packet.handshake

import com.github.quiltservertools.ledger.network.packet.LedgerPacket
import com.github.quiltservertools.ledger.network.packet.LedgerPacketTypes
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.minecraft.network.PacketByteBuf
import net.minecraft.util.Identifier

class HandshakePacket: LedgerPacket<HandshakeContent> {
    override val channel: Identifier = LedgerPacketTypes.HANDSHAKE.id
    override var buf: PacketByteBuf = PacketByteBufs.create()
    override fun populate(content: HandshakeContent) {
        // Ledger information
        // Protocol Version
        buf.writeInt(content.protocolVersion)

        // Ledger Version
        buf.writeString(content.ledgerVersion)

        // Is client mod allowed
        val allowed = content.allowed
        buf.writeBoolean(allowed)
    }
}
