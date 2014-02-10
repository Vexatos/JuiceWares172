package com.juicegrape.juicewares.tileentities;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityAltar extends TileEntity {

	private final Random random = new Random();
	
	public ItemStack book;
	
	public TileEntityAltar() {
		book = null;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagCompound nbt1 = nbt.getCompoundTag("book");
		if (nbt1 != null) {
			book = ItemStack.loadItemStackFromNBT(nbt1);
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		if (book != null) {
			NBTTagCompound nbt1 = new NBTTagCompound();
			book.writeToNBT(nbt1);
			nbt.setTag("book", nbt1);
		}
	}
	
	public void clearItem() {
		if (book != null) {
			EntityItem bookEnt = createItem(book);
			worldObj.spawnEntityInWorld(bookEnt);
			update();
			book = null;
		}
	}
	
	public void update() {
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	
	private EntityItem createItem(ItemStack itemStack) {
		float xThang = random.nextFloat() * 0.8F + 0.1F;
		float yThang = random.nextFloat() * 0.8F + 0.1F;
		float zThang = random.nextFloat() * 0.8F + 0.1F;
		EntityItem entityItem = new EntityItem(worldObj, xCoord + xThang, yCoord + yThang, zCoord + zThang, itemStack);
        entityItem.motionX = (float) random.nextGaussian() * 0.05F;
        entityItem.motionY = (float) random.nextGaussian() * 0.05F + 0.2F;
        entityItem.motionZ = (float) random.nextGaussian() * 0.05F;
        return entityItem;
	}
	
    @Override
    public Packet getDescriptionPacket() {
    	NBTTagCompound nbtTag = new NBTTagCompound();
    	writeToNBT(nbtTag);
    	return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbtTag);
    	
    }
    
    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    	readFromNBT(pkt.func_148857_g());
    }
    
    public void setBook(ItemStack stack) {
    	book = stack;
    	update();
    }
	
}
