package com.gtnewhorizon.newgunrizons.client.debug;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import org.lwjgl.input.Mouse;

public class PositionDebugScreen extends GuiScreen {

	private static final String[] SLIDER_LABELS = {
		"Position X", "Position Y", "Position Z",
		"Rotation X", "Rotation Y", "Rotation Z" };

	private static final float[] SLIDER_MINS = { -40, -40, -40, -180, -180, -180 };
	private static final float[] SLIDER_MAXS = { 40, 40, 40, 180, 180, 180 };
	private static final float[] SLIDER_STEPS = { 0.1f, 0.1f, 0.1f, 1.0f, 1.0f, 1.0f };
	private static final float[] SLIDER_SHIFT_STEPS = { 0.01f, 0.01f, 0.01f, 0.1f, 0.1f, 0.1f };
	private static final int SLIDER_COUNT = 6;

	private static final String SHOOTING_LABEL = "Shooting (ms)";
	private static final String CAM_RECOIL_LABEL = "Cam Recoil (ms)";
	private static final float DURATION_MIN = 10f;
	private static final float DURATION_MAX = 5000f;
	private static final float DURATION_STEP = 5f;

	private static final int TRACK_H = 10;
	private static final int SLIDER_GAP = 18;

	private static final int CLIP_BTN_BASE_ID = 100;
	private static final int BTN_PLAY = 2000;
	private static final int BTN_COPY_JSON = 2001;
	private static final int BTN_DISABLE = 2002;
	private static final int BTN_BONE_PREV = 2003;
	private static final int BTN_BONE_NEXT = 2004;

	private static final int SHOOTING_SLIDER_INDEX = 98;
	private static final int CAM_RECOIL_SLIDER_INDEX = 97;

	private int dragSlider = -1;
	private int sliderPanelX;
	private int sliderTrackX;
	private int sliderTrackW;
	private int boneSpinnerY;
	private int slidersStartY;
	private int shootingSliderY;
	private int camRecoilSliderY;

	// Clip list
	private int clipListScrollOffset;
	private int clipListX;
	private int clipListY;
	private int clipListW;
	private int clipListH;
	private static final int CLIP_BTN_H = 16;
	private static final int CLIP_BTN_GAP = 2;
	private List<String> clipNames;

	@Override
	public void initGui() {
		buttonList.clear();
		clipNames = PositionDebugger.getAvailableClipNames();

		// Left side: clip list
		clipListX = 5;
		clipListY = 8;
		clipListW = 150;
		clipListH = height - 50;

		// Right side: slider panel (compact, top-right)
		sliderPanelX = width - 250;
		sliderTrackX = sliderPanelX + 80;
		sliderTrackW = width - sliderTrackX - 55;
		if (sliderTrackW < 40) sliderTrackW = 40;

		// Bone spinner row
		boneSpinnerY = clipListY + 4;
		// Sliders start below bone spinner
		slidersStartY = boneSpinnerY + 20;
		shootingSliderY = slidersStartY + SLIDER_COUNT * SLIDER_GAP + 8;
		camRecoilSliderY = shootingSliderY + SLIDER_GAP;

		rebuildClipButtons();

		// Bone spinner buttons
		buttonList.add(new GuiButton(BTN_BONE_PREV, sliderPanelX, boneSpinnerY, 16, 14, "<"));
		buttonList.add(new GuiButton(BTN_BONE_NEXT, sliderPanelX + 18, boneSpinnerY, 16, 14, ">"));

		// Bottom action buttons (below clip list, always visible)
		int bottomY = height - 24;
		buttonList.add(new GuiButton(BTN_PLAY, clipListX, bottomY, 50, 16, "Play"));
		buttonList.add(new GuiButton(BTN_COPY_JSON, clipListX + 54, bottomY, 70, 16, "Copy JSON"));
		buttonList.add(new GuiButton(BTN_DISABLE, clipListX + 128, bottomY, 55, 16, "Disable"));
	}

	private void rebuildClipButtons() {
		// Remove ONLY clip buttons (keep action buttons)
		buttonList.removeIf(btn -> btn.id >= CLIP_BTN_BASE_ID && btn.id < CLIP_BTN_BASE_ID + 1000);

		int visibleCount = clipListH / (CLIP_BTN_H + CLIP_BTN_GAP);
		String selected = PositionDebugger.getSelectedClipName();

		for (int i = 0; i < clipNames.size(); i++) {
			int displayIndex = i - clipListScrollOffset;
			if (displayIndex < 0 || displayIndex >= visibleCount) continue;

			int btnY = clipListY + displayIndex * (CLIP_BTN_H + CLIP_BTN_GAP);
			String name = clipNames.get(i);
			String displayName = name;
			if (fontRendererObj != null && fontRendererObj.getStringWidth(displayName) > clipListW - 8) {
				while (fontRendererObj.getStringWidth(displayName + "...") > clipListW - 8
					&& displayName.length() > 3) {
					displayName = displayName.substring(0, displayName.length() - 1);
				}
				displayName = displayName + "...";
			}

			GuiButton btn = new GuiButton(CLIP_BTN_BASE_ID + i, clipListX, btnY, clipListW, CLIP_BTN_H, displayName);
			btn.enabled = !name.equals(selected);
			buttonList.add(btn);
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		if (!Mouse.isButtonDown(0)) {
			dragSlider = -1;
		}

		// Red target crosshair at screen center
		int cx = width / 2;
		int cy = height / 2;
		drawRect(cx - 1, cy - 5, cx + 1, cy + 5, 0xFFFF0000);
		drawRect(cx - 5, cy - 1, cx + 5, cy + 1, 0xFFFF0000);

		// Left panel background (clip list)
		drawRect(clipListX - 2, clipListY - 2, clipListX + clipListW + 2,
			clipListY + clipListH + 2, 0xC0101010);

		// Right panel background (sliders only, compact)
		int sliderPanelBottom = camRecoilSliderY + SLIDER_GAP + 4;
		drawRect(sliderPanelX - 4, clipListY - 2, width - 1, sliderPanelBottom, 0xC0101010);

		// Bone spinner label
		String boneName = PositionDebugger.getSelectedBoneName();
		fontRendererObj.drawStringWithShadow("Bone: " + (boneName != null ? boneName : "?"),
			sliderPanelX + 38, boneSpinnerY + 3, 0x55FFFF);

		// Draw 6 position/rotation sliders
		for (int i = 0; i < SLIDER_COUNT; i++) {
			int y = slidersStartY + i * SLIDER_GAP;
			drawSlider(i, y, SLIDER_LABELS[i], getSliderValue(i),
				SLIDER_MINS[i], SLIDER_MAXS[i], mouseX);
		}

		// Shooting duration slider
		int shootingVal = PositionDebugger.getShootingDurationOverride();
		float shootingDisplay = shootingVal >= 0 ? shootingVal : 100f;
		drawSlider(SHOOTING_SLIDER_INDEX, shootingSliderY, SHOOTING_LABEL,
			shootingDisplay, DURATION_MIN, DURATION_MAX, mouseX);
		if (shootingVal < 0) {
			fontRendererObj.drawStringWithShadow("(def)",
				sliderTrackX + sliderTrackW + 38, shootingSliderY + 1, 0x888888);
		}

		// Camera recoil duration slider
		int camRecoilVal = PositionDebugger.getCameraRecoilDurationOverride();
		float camRecoilDisplay = camRecoilVal >= 0 ? camRecoilVal : 50f;
		drawSlider(CAM_RECOIL_SLIDER_INDEX, camRecoilSliderY, CAM_RECOIL_LABEL,
			camRecoilDisplay, DURATION_MIN, DURATION_MAX, mouseX);
		if (camRecoilVal < 0) {
			fontRendererObj.drawStringWithShadow("(def)",
				sliderTrackX + sliderTrackW + 38, camRecoilSliderY + 1, 0x888888);
		}

		// Status bar
		String selectedClip = PositionDebugger.getSelectedClipName();
		fontRendererObj.drawStringWithShadow(
			"Clip: " + (selectedClip != null ? selectedClip : "none"),
			clipListX, height - 10, 0x55FF55);

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	private void drawSlider(int index, int y, String label, float value,
		float min, float max, int mouseX) {
		fontRendererObj.drawStringWithShadow(label, sliderPanelX, y + 1, 0xCCCCCC);

		drawRect(sliderTrackX, y, sliderTrackX + sliderTrackW, y + TRACK_H, 0xFF333333);
		drawRect(sliderTrackX, y, sliderTrackX + sliderTrackW, y + 1, 0xFF555555);
		drawRect(sliderTrackX, y, sliderTrackX + 1, y + TRACK_H, 0xFF555555);

		// Zero line
		if (min < 0 && max > 0) {
			float zeroRatio = -min / (max - min);
			int zeroX = sliderTrackX + (int) (zeroRatio * sliderTrackW);
			drawRect(zeroX, y, zeroX + 1, y + TRACK_H, 0xFF666666);
		}

		// Handle
		float ratio = Math.max(0, Math.min(1, (value - min) / (max - min)));
		int handleX = sliderTrackX + (int) (ratio * (sliderTrackW - 4));
		int handleColor = dragSlider == index ? 0xFFFFFF55 : 0xFFFFFFFF;
		drawRect(handleX, y, handleX + 4, y + TRACK_H, handleColor);

		// Value text
		boolean isDuration = index == SHOOTING_SLIDER_INDEX || index == CAM_RECOIL_SLIDER_INDEX;
		String fmt = isDuration ? "%.0f" : (index < 3 ? "%.2f" : "%.1f");
		fontRendererObj.drawStringWithShadow(String.format(fmt, value),
			sliderTrackX + sliderTrackW + 4, y + 1, 0xAAAAFF);
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		if (button.id >= CLIP_BTN_BASE_ID && button.id < CLIP_BTN_BASE_ID + 1000) {
			int clipIndex = button.id - CLIP_BTN_BASE_ID;
			if (clipIndex >= 0 && clipIndex < clipNames.size()) {
				PositionDebugger.selectClip(clipNames.get(clipIndex));
				rebuildClipButtons();
			}
		} else if (button.id == BTN_PLAY) {
			PositionDebugger.playSelectedClip();
		} else if (button.id == BTN_COPY_JSON) {
			String json = PositionDebugger.generateJson();
			try {
				Toolkit.getDefaultToolkit()
					.getSystemClipboard()
					.setContents(new StringSelection(json), null);
			} catch (Exception ignored) {}
		} else if (button.id == BTN_DISABLE) {
			PositionDebugger.setActive(false);
			PositionDebugger.cleanup();
			mc.displayGuiScreen(null);
		} else if (button.id == BTN_BONE_PREV) {
			PositionDebugger.prevBone();
		} else if (button.id == BTN_BONE_NEXT) {
			PositionDebugger.nextBone();
		}
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
		super.mouseClicked(mouseX, mouseY, mouseButton);

		for (int i = 0; i < SLIDER_COUNT; i++) {
			int y = slidersStartY + i * SLIDER_GAP;
			if (mouseX >= sliderTrackX && mouseX <= sliderTrackX + sliderTrackW
				&& mouseY >= y && mouseY <= y + TRACK_H) {
				if (mouseButton == 0) {
					dragSlider = i;
					updateSliderFromMouse(i, mouseX);
				} else if (mouseButton == 1) {
					resetSliderToInitial(i);
				}
				return;
			}
		}

		if (mouseX >= sliderTrackX && mouseX <= sliderTrackX + sliderTrackW
			&& mouseY >= shootingSliderY && mouseY <= shootingSliderY + TRACK_H) {
			if (mouseButton == 0) {
				dragSlider = SHOOTING_SLIDER_INDEX;
				updateDurationFromMouse(SHOOTING_SLIDER_INDEX, mouseX);
			} else if (mouseButton == 1) {
				PositionDebugger.setShootingDurationOverride(-1);
			}
			return;
		}

		if (mouseX >= sliderTrackX && mouseX <= sliderTrackX + sliderTrackW
			&& mouseY >= camRecoilSliderY && mouseY <= camRecoilSliderY + TRACK_H) {
			if (mouseButton == 0) {
				dragSlider = CAM_RECOIL_SLIDER_INDEX;
				updateDurationFromMouse(CAM_RECOIL_SLIDER_INDEX, mouseX);
			} else if (mouseButton == 1) {
				PositionDebugger.setCameraRecoilDurationOverride(-1);
			}
		}
	}

	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
		if (dragSlider >= 0 && clickedMouseButton == 0) {
			if (dragSlider == SHOOTING_SLIDER_INDEX || dragSlider == CAM_RECOIL_SLIDER_INDEX) {
				updateDurationFromMouse(dragSlider, mouseX);
			} else {
				updateSliderFromMouse(dragSlider, mouseX);
			}
		}
	}

	@Override
	public void handleMouseInput() {
		super.handleMouseInput();
		int scroll = Mouse.getEventDWheel();
		if (scroll != 0) {
			int mouseX = Mouse.getEventX() * this.width / this.mc.displayWidth;
			int mouseY = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;

			// Clip list scroll
			if (mouseX >= clipListX && mouseX <= clipListX + clipListW
				&& mouseY >= clipListY && mouseY <= clipListY + clipListH) {
				int visibleCount = clipListH / (CLIP_BTN_H + CLIP_BTN_GAP);
				int maxScroll = Math.max(0, clipNames.size() - visibleCount);
				clipListScrollOffset += (scroll > 0 ? -1 : 1);
				clipListScrollOffset = Math.max(0, Math.min(maxScroll, clipListScrollOffset));
				rebuildClipButtons();
				return;
			}

			// Slider scroll
			for (int i = 0; i < SLIDER_COUNT; i++) {
				int y = slidersStartY + i * SLIDER_GAP;
				if (mouseX >= sliderPanelX && mouseX <= width && mouseY >= y && mouseY <= y + TRACK_H) {
					float step = isShiftKeyDown() ? SLIDER_SHIFT_STEPS[i] : SLIDER_STEPS[i];
					float current = getSliderValue(i);
					float newVal = current + (scroll > 0 ? step : -step);
					newVal = Math.max(SLIDER_MINS[i], Math.min(SLIDER_MAXS[i], newVal));
					setSliderValue(i, newVal);
					PositionDebugger.applyDebugOffset();
					return;
				}
			}

			if (mouseX >= sliderPanelX && mouseX <= width
				&& mouseY >= shootingSliderY && mouseY <= shootingSliderY + TRACK_H) {
				scrollDurationSlider(scroll, SHOOTING_SLIDER_INDEX);
				return;
			}
			if (mouseX >= sliderPanelX && mouseX <= width
				&& mouseY >= camRecoilSliderY && mouseY <= camRecoilSliderY + TRACK_H) {
				scrollDurationSlider(scroll, CAM_RECOIL_SLIDER_INDEX);
			}
		}
	}

	private void updateSliderFromMouse(int index, int mouseX) {
		float ratio = (float) (mouseX - sliderTrackX) / (float) sliderTrackW;
		ratio = Math.max(0, Math.min(1, ratio));
		float value = SLIDER_MINS[index] + ratio * (SLIDER_MAXS[index] - SLIDER_MINS[index]);
		float step = isShiftKeyDown() ? SLIDER_SHIFT_STEPS[index] : SLIDER_STEPS[index];
		value = Math.round(value / step) * step;
		setSliderValue(index, value);
		PositionDebugger.applyDebugOffset();
	}

	private void updateDurationFromMouse(int sliderIndex, int mouseX) {
		float ratio = (float) (mouseX - sliderTrackX) / (float) sliderTrackW;
		ratio = Math.max(0, Math.min(1, ratio));
		float value = DURATION_MIN + ratio * (DURATION_MAX - DURATION_MIN);
		float step = isShiftKeyDown() ? 1f : DURATION_STEP;
		value = Math.round(value / step) * step;
		setDurationOverride(sliderIndex, Math.round(value));
	}

	private void scrollDurationSlider(int scroll, int sliderIndex) {
		float step = isShiftKeyDown() ? 1f : DURATION_STEP;
		int current = getDurationOverride(sliderIndex);
		if (current < 0) current = 50;
		float newVal = current + (scroll > 0 ? step : -step);
		newVal = Math.max(DURATION_MIN, Math.min(DURATION_MAX, newVal));
		setDurationOverride(sliderIndex, Math.round(newVal));
	}

	private int getDurationOverride(int sliderIndex) {
		if (sliderIndex == SHOOTING_SLIDER_INDEX) return PositionDebugger.getShootingDurationOverride();
		return PositionDebugger.getCameraRecoilDurationOverride();
	}

	private void setDurationOverride(int sliderIndex, int value) {
		if (sliderIndex == SHOOTING_SLIDER_INDEX) {
			PositionDebugger.setShootingDurationOverride(value);
		} else {
			PositionDebugger.setCameraRecoilDurationOverride(value);
		}
	}

	private float getSliderValue(int index) {
		switch (index) {
			case 0: return PositionDebugger.getPosX();
			case 1: return PositionDebugger.getPosY();
			case 2: return PositionDebugger.getPosZ();
			case 3: return PositionDebugger.getRotX();
			case 4: return PositionDebugger.getRotY();
			case 5: return PositionDebugger.getRotZ();
			default: return 0;
		}
	}

	private void setSliderValue(int index, float value) {
		switch (index) {
			case 0: PositionDebugger.setPosX(value); break;
			case 1: PositionDebugger.setPosY(value); break;
			case 2: PositionDebugger.setPosZ(value); break;
			case 3: PositionDebugger.setRotX(value); break;
			case 4: PositionDebugger.setRotY(value); break;
			case 5: PositionDebugger.setRotZ(value); break;
		}
	}

	private void resetSliderToInitial(int index) {
		switch (index) {
			case 0: PositionDebugger.setPosX(PositionDebugger.getInitialPosX()); break;
			case 1: PositionDebugger.setPosY(PositionDebugger.getInitialPosY()); break;
			case 2: PositionDebugger.setPosZ(PositionDebugger.getInitialPosZ()); break;
			case 3: PositionDebugger.setRotX(PositionDebugger.getInitialRotX()); break;
			case 4: PositionDebugger.setRotY(PositionDebugger.getInitialRotY()); break;
			case 5: PositionDebugger.setRotZ(PositionDebugger.getInitialRotZ()); break;
		}
		PositionDebugger.applyDebugOffset();
	}

	@Override
	public void onGuiClosed() {
		PositionDebugger.cleanup();
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
