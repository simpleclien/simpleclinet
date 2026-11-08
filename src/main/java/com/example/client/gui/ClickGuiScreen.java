package com.example.client.gui;

import com.example.client.SimpleClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ClickGuiScreen extends Screen {

    public ClickGuiScreen() {
        super(Text.literal("SimpleClient GUI"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);

        int panelX = this.width / 2 - 100;
        int panelY = this.height / 2 - 120;
        int panelWidth = 200;
        int panelHeight = 240;

        context.fill(panelX, panelY, panelX + panelWidth, panelY + panelHeight, 0xFF121212);
        context.fill(panelX, panelY, panelX + panelWidth, panelY + 25, 0xFF1E1E1E);
        
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, panelY + 8, 0xFFFFFFFF);

        drawButton(context, panelX + 20, panelY + 40, panelWidth - 40, 30, "Freecam", SimpleClient.freecamEnabled, mouseX, mouseY);
        drawButton(context, panelX + 20, panelY + 80, panelWidth - 40, 30, "Storage ESP", SimpleClient.storageEspEnabled, mouseX, mouseY);
        drawButton(context, panelX + 20, panelY + 120, panelWidth - 40, 30, "Spawner Finder", SimpleClient.spawnerFinderEnabled, mouseX, mouseY);
        drawButton(context, panelX + 20, panelY + 160, panelWidth - 40, 30, "Player ESP", SimpleClient.playerEspEnabled, mouseX, mouseY);

        super.render(context, mouseX, mouseY, delta);
    }

    private void drawButton(DrawContext context, int x, int y, int width, int height, String label, boolean enabled, int mouseX, int mouseY) {
        boolean hovered = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
        int bgColor = enabled ? 0xFF1B4D2E : (hovered ? 0xFF2A2A2A : 0xFF1F1F1F);
        
        context.fill(x, y, x + width, y + height, bgColor);
        context.drawBorder(x, y, width, height, 0xFF333333);

        int textColor = enabled ? 0xFF55FF55 : 0xFFAAAAAA;
        context.drawTextWithShadow(this.textRenderer, label, x + 8, y + 11, 0xFFFFFFFF);
        context.drawTextWithShadow(this.textRenderer, enabled ? "ON" : "OFF", x + width - 35, y + 11, textColor);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int panelX = this.width / 2 - 100;
        int panelY = this.height / 2 - 120;
        int panelWidth = 200;

        if (isInside(mouseX, mouseY, panelX + 20, panelY + 40, panelWidth - 40, 30)) {
            SimpleClient.freecamEnabled = !SimpleClient.freecamEnabled;
            return true;
        }
        if (isInside(mouseX, mouseY, panelX + 20, panelY + 80, panelWidth - 40, 30)) {
            SimpleClient.storageEspEnabled = !SimpleClient.storageEspEnabled;
            return true;
        }
        if (isInside(mouseX, mouseY, panelX + 20, panelY + 120, panelWidth - 40, 30)) {
            SimpleClient.spawnerFinderEnabled = !SimpleClient.spawnerFinderEnabled;
            return true;
        }
        if (isInside(mouseX, mouseY, panelX + 20, panelY + 160, panelWidth - 40, 30)) {
            SimpleClient.playerEspEnabled = !SimpleClient.playerEspEnabled;
            return true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    private boolean isInside(double mx, double my, int x, int y, int w, int h) {
        return mx >= x && mx <= x + w && my >= y && my <= y + h;
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
