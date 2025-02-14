package com.github.quiltservertools.ledger.listeners

import com.github.quiltservertools.ledger.actionutils.ActionFactory
import com.github.quiltservertools.ledger.callbacks.ItemInsertCallback
import com.github.quiltservertools.ledger.callbacks.ItemRemoveCallback
import com.github.quiltservertools.ledger.database.DatabaseManager
import net.minecraft.item.ItemStack
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos

fun registerWorldEventListeners() {
    ItemInsertCallback.EVENT.register(::onItemInsert)
    ItemRemoveCallback.EVENT.register(::onItemRemove)
}

private fun onItemRemove(
    stack: ItemStack,
    pos: BlockPos,
    world: ServerWorld,
    source: String,
    player: ServerPlayerEntity?
) {
    if (player != null) {
        DatabaseManager.logAction(
            ActionFactory.itemRemoveAction(world, stack, pos, player)
        )
    } else {
        DatabaseManager.logAction(
            ActionFactory.itemRemoveAction(world, stack, pos, source)
        )
    }
}

private fun onItemInsert(
    stack: ItemStack,
    pos: BlockPos,
    world: ServerWorld,
    source: String,
    player: ServerPlayerEntity?
) {
    if (player != null) {
        DatabaseManager.logAction(
            ActionFactory.itemInsertAction(world, stack, pos, player)
        )
    } else {
        DatabaseManager.logAction(
            ActionFactory.itemInsertAction(world, stack, pos, source)
        )
    }
}
