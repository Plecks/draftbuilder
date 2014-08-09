package com.plecks.draftbuilder;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraftforge.client.event.RenderWorldLastEvent;

import org.lwjgl.opengl.GL11;

import com.plecks.common.BasicBlock;
import com.plecks.common.PlecksCore;
import com.plecks.common.Vector;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/** Handles the graphical elements of DraftBuilder */
public class GUI{
	
	@SubscribeEvent
	public void renderWorldLastEvent(RenderWorldLastEvent evt)
	{
		GL11.glPushMatrix();
		double playerX = PlecksCore.getPlayer().lastTickPosX + (PlecksCore.getPlayer().posX - PlecksCore.getPlayer().lastTickPosX) * evt.partialTicks;
	    double playerY = PlecksCore.getPlayer().lastTickPosY + (PlecksCore.getPlayer().posY - PlecksCore.getPlayer().lastTickPosY) * evt.partialTicks;
	    double playerZ = PlecksCore.getPlayer().lastTickPosZ + (PlecksCore.getPlayer().posZ - PlecksCore.getPlayer().lastTickPosZ) * evt.partialTicks;
		GL11.glTranslated(-playerX, -playerY, -playerZ); //Translates to the target player
		
		
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
	    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
	    if(DraftBuilder.instance.selection.checkPoints())
	    {
		    //Rendering the selection points
		    GL11.glDisable(GL11.GL_DEPTH_TEST);
		    Vector pos1 = DraftBuilder.instance.selection.pos1;
		    Vector pos2 = DraftBuilder.instance.selection.pos2;
		    renderColoredBlock(pos1.getBlockX(),pos1.getBlockY(),pos1.getBlockZ(),0.0f,1.0f,0.0f,0.3f);
		    renderColoredBlock(pos2.getBlockX(),pos2.getBlockY(),pos2.getBlockZ(),1.0f,1.0f,0.5f,0.3f);
		    GL11.glLineWidth(2f);
		    Vector min = DraftBuilder.instance.selection.minPoint();
		    Vector max = DraftBuilder.instance.selection.maxPoint();
		    renderWireframeBox(min.getBlockX(),min.getBlockY(),min.getBlockZ(),max.getBlockX(),max.getBlockY(),max.getBlockZ(),1.0f,0.0f,0.0f,0.3f);
		    GL11.glEnable(GL11.GL_DEPTH_TEST);
	    }
	    
	    if (false) //TODO Config option for rendering through walls
	    {
	    	GL11.glDisable(GL11.GL_DEPTH_TEST);
	    }
	    else
	    {
	        GL11.glEnable(GL11.GL_DEPTH_TEST);
	    }
	    //Rendering the changelist
	    List<BasicBlock> changeList = DraftBuilder.instance.manipulator.getChangeList();
	    for(BasicBlock block : changeList)
	    {
	    	if(block.blockID != 0)
	    	{
	    		Block curBlock = PlecksCore.getWorld().getBlock(block.getX(), block.getY(), block.getZ());
				int curBlockMeta = PlecksCore.getWorld().getBlockMetadata(block.getX(), block.getY(), block.getZ());
	    		
				if(curBlock.getMaterial().isReplaceable())
	    		{
	    			renderColoredBlock(block.getX(),block.getY(),block.getZ(),0.0f,0.0f,.75f,0.2f);
	    		}
	    		else
	    		{
	    			renderColoredBlock(block.getX(),block.getY(),block.getZ(),.75f,0.0f,.75f,0.2f);
	    		}
	    	}
	    	else
	    	{
	    		renderColoredBlock(block.getX(),block.getY(),block.getZ(),1.0f,0.0f,0.0f,0.2f);
	    	}
	    }
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	    GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ZERO);
		GL11.glPopMatrix();
		}
		

	public void renderColoredBlock(int x, int y, int z, float r, float g, float b, float alpha)
	{
		
		GL11.glColor4f(r, g, b, alpha);
		 //East side
		GL11.glBegin(GL11.GL_POLYGON);
		GL11.glVertex3f( -0.001f + x,  1.001f + y, -0.001f + z );
		GL11.glVertex3f(  1.001f + x,  1.001f + y, -0.001f + z );
		GL11.glVertex3f(  1.001f + x, -0.001f + y, -0.001f + z );
		GL11.glVertex3f( -0.001f + x, -0.001f + y, -0.001f + z );
		GL11.glEnd();

		//West
		GL11.glBegin(GL11.GL_POLYGON);
		GL11.glVertex3f(  1.001f + x, -0.001f + y, 1.001f + z );
		GL11.glVertex3f(  1.001f + x,  1.001f + y, 1.001f + z );
		GL11.glVertex3f( -0.001f + x,  1.001f + y, 1.001f + z );
		GL11.glVertex3f( -0.001f + x, -0.001f + y, 1.001f + z );
		GL11.glEnd();

		//South
		GL11.glBegin(GL11.GL_POLYGON);
		GL11.glVertex3f( 1.001f + x, -0.001f + y, -0.001f + z );
		GL11.glVertex3f( 1.001f + x,  1.001f + y, -0.001f + z );
		GL11.glVertex3f( 1.001f + x,  1.001f + y,  1.001f + z );
		GL11.glVertex3f( 1.001f + x, -0.001f + y,  1.001f + z );
		GL11.glEnd();

		//North
		GL11.glBegin(GL11.GL_POLYGON);
		GL11.glVertex3f( -0.001f + x, -0.001f + y,  1.001f  + z);
		GL11.glVertex3f( -0.001f + x,  1.001f + y,  1.001f + z );
		GL11.glVertex3f( -0.001f + x,  1.001f + y, -0.001f + z );
		GL11.glVertex3f( -0.001f + x, -0.001f + y, -0.001f + z );
		GL11.glEnd();

		//Top
		GL11.glBegin(GL11.GL_POLYGON);
		GL11.glVertex3f(  1.001f + x,  1.001f + y,  1.001f  + z);
		GL11.glVertex3f(  1.001f + x,  1.001f + y, -0.001f  + z);
		GL11.glVertex3f( -0.001f + x,  1.001f + y, -0.001f  + z);
		GL11.glVertex3f( -0.001f + x,  1.001f + y,  1.001f  + z);
		GL11.glEnd();

		//Bottom
		GL11.glBegin(GL11.GL_POLYGON);
		GL11.glVertex3f(  1.001f + x, -0.001f + y, -0.001f + z );
		GL11.glVertex3f(  1.001f + x, -0.001f + y,  1.001f + z );
		GL11.glVertex3f( -0.001f + x, -0.001f + y,  1.001f + z );
		GL11.glVertex3f( -0.001f + x, -0.001f + y, -0.001f + z );
		GL11.glEnd();		
	}
	
	public void renderWireframeBox(int x1, int y1, int z1, int x2, int y2, int z2, float r, float g, float b, float alpha)
	{
		GL11.glColor4f(r, g, b, alpha);
		
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK,GL11.GL_LINE);
		GL11.glLineWidth(3f);
		 //FRONT
		GL11.glBegin(GL11.GL_POLYGON);
		GL11.glVertex3f( x1, y2 + 1, z1 );
		GL11.glVertex3f( x2 + 1, y2 + 1, z1 );
		GL11.glVertex3f( x2 + 1, y1, z1 );
		GL11.glVertex3f( x1, y1, z1 );
		GL11.glEnd();

		//BACK
		GL11.glBegin(GL11.GL_POLYGON);
		GL11.glVertex3f(  x2 + 1, y1, z2 + 1 );
		GL11.glVertex3f(  x2 + 1,  y2 + 1, z2 + 1 );
		GL11.glVertex3f( x1,  y2 + 1, z2 + 1 );
		GL11.glVertex3f( x1, y1, z2 + 1 );
		GL11.glEnd();

		//RIGHT
		GL11.glBegin(GL11.GL_POLYGON);
		GL11.glVertex3f( x2 + 1, y1, z1 );
		GL11.glVertex3f( x2 + 1,  y2 + 1, z1 );
		GL11.glVertex3f( x2 + 1,  y2 + 1,  z2 + 1 );
		GL11.glVertex3f( x2 + 1, y1,  z2 + 1 );
		GL11.glEnd();

		//LEFT
		GL11.glBegin(GL11.GL_POLYGON);
		GL11.glVertex3f( x1, y1,  z2 + 1);
		GL11.glVertex3f( x1,  y2 + 1,  z2 + 1 );
		GL11.glVertex3f( x1,  y2 + 1, z1 );
		GL11.glVertex3f( x1, y1, z1 );
		GL11.glEnd();

		//TOP
		GL11.glBegin(GL11.GL_POLYGON);
		GL11.glVertex3f(  x2 + 1,  y2 + 1,  z2 + 1);
		GL11.glVertex3f(  x2 + 1,  y2 + 1, z1);
		GL11.glVertex3f( x1,  y2 + 1, z1);
		GL11.glVertex3f( x1,  y2 + 1,  z2 + 1);
		GL11.glEnd();

		//BOTTOM
		GL11.glBegin(GL11.GL_POLYGON);
		GL11.glVertex3f(  x2 + 1, y1, z1 );
		GL11.glVertex3f(  x2 + 1, y1,  z2 + 1 );
		GL11.glVertex3f( x1, y1,  z2 + 1 );
		GL11.glVertex3f( x1, y1, z1 );
		GL11.glEnd();
		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK,GL11.GL_FILL);
		GL11.glEnable(GL11.GL_CULL_FACE);
	}
}
