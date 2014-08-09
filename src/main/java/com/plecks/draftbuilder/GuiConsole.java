package com.plecks.draftbuilder;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ChatAllowedCharacters;

import org.lwjgl.input.Keyboard;

import com.plecks.draftbuilder.commands.CommandException;

public class GuiConsole extends GuiScreen
{
	private static final GuiConsole INSTANCE = new GuiConsole();
	
	protected GuiTextField inputField;
    
	private static final char[] allowedCharacters;

	public void initGui()
	{
		Keyboard.enableRepeatEvents(true);
		this.inputField = new GuiTextField(this.fontRendererObj, this.width / 4, 10, this.width / 2, 12);
        this.inputField.setMaxStringLength(100);
        this.inputField.setEnableBackgroundDrawing(false);
        this.inputField.setFocused(true);
        this.inputField.setCanLoseFocus(false);
	}

	public static GuiConsole instance()
	{
		return INSTANCE;
	}
	
	protected void keyTyped(char par1, int par2)
    {
        if (par2 == Keyboard.KEY_ESCAPE || par2 == Keybinds.toggleConsole.getKeyCode())
        {
            this.mc.displayGuiScreen((GuiScreen)null);
        }
        else if (par2 == Keyboard.KEY_RETURN)
        {
            String s = this.inputField.getText().trim();

            if(!s.isEmpty()){
	            try {
					DraftBuilder.instance.commandHandler.execute(s.split(" "));
				} catch (CommandException e) {
					
					e.printStackTrace();
				}
            }

            this.mc.displayGuiScreen((GuiScreen)null);
        }
        else if (par2 == Keyboard.KEY_TAB || par2 == Keyboard.KEY_RIGHT)
        {
        	//TODO Predictive text
        }
        else if (par2 == Keyboard.KEY_UP)
        {
        	//TODO History
        }
        else if (par2 == Keyboard.KEY_DOWN)
        {
        	//TODO History
        }
        else
        {
            this.inputField.textboxKeyTyped(par1, par2);
        }
    }

	/**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
        drawRect(this.width / 4 - 2, 8, this.width / 4 * 3 + 6, 20, Integer.MIN_VALUE);
        this.inputField.drawTextBox();
        super.drawScreen(par1, par2, par3);
    }
    
    public void updateScreen()
    {
        this.inputField.updateCursorCounter();
    }

	static
	{
		allowedCharacters = ChatAllowedCharacters.allowedCharacters;
	}
}
