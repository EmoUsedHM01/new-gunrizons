package com.gtnewhorizon.newgunrizons.client.debug;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import com.gtnewhorizon.newgunrizons.client.animation.BedrockAnimation;
import com.gtnewhorizon.newgunrizons.client.animation.BedrockAnimation.AnimationClip;
import com.gtnewhorizon.newgunrizons.client.animation.BedrockAnimation.BoneAnimation;
import com.gtnewhorizon.newgunrizons.client.animation.BedrockAnimation.Keyframe;
import com.gtnewhorizon.newgunrizons.client.animation.BedrockAnimationController;

/**
 * Debug tool for adjusting weapon bone positions/rotations in real-time
 * using the Bedrock animation system. Works by applying additive offsets
 * to a selected bone on the animation controller.
 */
public class PositionDebugger {

	@Setter
	@Getter
	private static boolean active;

	@Setter
	@Getter
	private static boolean screenOpen;

	/** Override for shooting animation duration (ms). -1 means use weapon's default. */
	@Setter
	@Getter
	private static int shootingDurationOverride = -1;

	/** Override for camera recoil duration (ms). -1 means use weapon's default. */
	@Setter
	@Getter
	private static int cameraRecoilDurationOverride = -1;

	private static BedrockAnimationController currentController;
	private static String selectedClipName;
	@Getter
	private static String selectedBoneName = "receiver";
	private static List<String> availableBones = new ArrayList<>();

	// Current absolute slider values (Bedrock coords / degrees)
	@Getter @Setter
	private static float posX;
	@Getter @Setter
	private static float posY;
	@Getter @Setter
	private static float posZ;
	@Getter @Setter
	private static float rotX;
	@Getter @Setter
	private static float rotY;
	@Getter @Setter
	private static float rotZ;

	// Initial values from the selected bone at t=0
	@Getter
	private static float initialPosX;
	@Getter
	private static float initialPosY;
	@Getter
	private static float initialPosZ;
	@Getter
	private static float initialRotX;
	@Getter
	private static float initialRotY;
	@Getter
	private static float initialRotZ;

	/**
	 * Sets the current animation controller and selects the first clip.
	 */
	public static void setCurrentController(BedrockAnimationController ctrl) {
		currentController = ctrl;
		List<String> clips = getAvailableClipNames();
		if (!clips.isEmpty()) {
			selectClip(clips.get(0));
		}
	}

	/**
	 * Returns the list of clip names available in the current controller's animation.
	 */
	public static List<String> getAvailableClipNames() {
		if (currentController == null) return Collections.emptyList();
		BedrockAnimation anim = currentController.getAnimation();
		if (anim == null) return Collections.emptyList();
		return new ArrayList<>(anim.getClips().keySet());
	}

	/**
	 * Returns the currently selected clip name.
	 */
	public static String getSelectedClipName() {
		return selectedClipName;
	}

	/**
	 * Returns the list of bone names in the currently selected clip.
	 */
	public static List<String> getAvailableBones() {
		return availableBones;
	}

	/**
	 * Selects a clip and rebuilds the bone list. Keeps the current bone if it
	 * exists in the new clip, otherwise defaults to the first bone.
	 */
	public static void selectClip(String clipName) {
		selectedClipName = clipName;
		rebuildBoneList();

		// Keep current bone if available, else pick first
		if (!availableBones.contains(selectedBoneName)) {
			selectedBoneName = availableBones.isEmpty() ? "receiver" : availableBones.get(0);
		}

		loadBoneInitialValues();

		if (currentController != null) {
			currentController.clearDebugOffset();
		}
	}

	/**
	 * Selects a bone and loads its initial values from the current clip.
	 */
	public static void selectBone(String boneName) {
		selectedBoneName = boneName;
		loadBoneInitialValues();

		if (currentController != null) {
			currentController.clearDebugOffset();
		}
	}

	/**
	 * Cycles to the next bone in the list (wraps around).
	 */
	public static void nextBone() {
		if (availableBones.isEmpty()) return;
		int idx = availableBones.indexOf(selectedBoneName);
		idx = (idx + 1) % availableBones.size();
		selectBone(availableBones.get(idx));
	}

	/**
	 * Cycles to the previous bone in the list (wraps around).
	 */
	public static void prevBone() {
		if (availableBones.isEmpty()) return;
		int idx = availableBones.indexOf(selectedBoneName);
		idx = (idx - 1 + availableBones.size()) % availableBones.size();
		selectBone(availableBones.get(idx));
	}

	private static void rebuildBoneList() {
		availableBones.clear();
		if (currentController == null || selectedClipName == null) return;
		BedrockAnimation anim = currentController.getAnimation();
		if (anim == null) return;
		AnimationClip clip = anim.getClip(selectedClipName);
		if (clip == null) return;
		availableBones.addAll(clip.bones.keySet());
	}

	private static void loadBoneInitialValues() {
		initialPosX = 0;
		initialPosY = 0;
		initialPosZ = 0;
		initialRotX = 0;
		initialRotY = 0;
		initialRotZ = 0;

		if (currentController != null && selectedClipName != null && selectedBoneName != null) {
			BedrockAnimation anim = currentController.getAnimation();
			if (anim != null) {
				AnimationClip clip = anim.getClip(selectedClipName);
				if (clip != null) {
					BoneAnimation boneAnim = clip.bones.get(selectedBoneName);
					if (boneAnim != null) {
						if (boneAnim.rotation != null && !boneAnim.rotation.isEmpty()) {
							float[] rot = boneAnim.rotation.get(0).value;
							initialRotX = rot[0];
							initialRotY = rot[1];
							initialRotZ = rot[2];
						}
						if (boneAnim.position != null && !boneAnim.position.isEmpty()) {
							float[] pos = boneAnim.position.get(0).value;
							initialPosX = pos[0];
							initialPosY = pos[1];
							initialPosZ = pos[2];
						}
					}
				}
			}
		}

		posX = initialPosX;
		posY = initialPosY;
		posZ = initialPosZ;
		rotX = initialRotX;
		rotY = initialRotY;
		rotZ = initialRotZ;
	}

	/**
	 * Computes the delta between current slider values and initial values,
	 * then applies it as a debug offset to the controller.
	 */
	public static void applyDebugOffset() {
		if (currentController == null) return;

		float dpx = posX - initialPosX;
		float dpy = posY - initialPosY;
		float dpz = posZ - initialPosZ;
		float drx = rotX - initialRotX;
		float dry = rotY - initialRotY;
		float drz = rotZ - initialRotZ;

		boolean hasPos = dpx != 0 || dpy != 0 || dpz != 0;
		boolean hasRot = drx != 0 || dry != 0 || drz != 0;

		currentController.setDebugOffset(
			selectedBoneName,
			hasPos ? new float[] { dpx, dpy, dpz } : null,
			hasRot ? new float[] { drx, dry, drz } : null);
	}

	/**
	 * Plays the currently selected clip directly on the controller.
	 */
	public static void playSelectedClip() {
		if (currentController != null && selectedClipName != null) {
			currentController.playClipDirect(selectedClipName);
		}
	}

	/**
	 * Generates a JSON string for the selected clip with the current slider values
	 * for the selected bone.
	 */
	public static String generateJson() {
		if (currentController == null || selectedClipName == null) return "{}";

		BedrockAnimation anim = currentController.getAnimation();
		if (anim == null) return "{}";

		AnimationClip clip = anim.getClip(selectedClipName);
		if (clip == null) return "{}";

		StringBuilder sb = new StringBuilder();
		sb.append("\"").append(selectedClipName).append("\": {\n");
		sb.append("  \"animation_length\": ").append(formatFloat(clip.length)).append(",\n");
		if (clip.loop) {
			sb.append("  \"loop\": true,\n");
		}
		sb.append("  \"bones\": {\n");

		boolean firstBone = true;
		for (Map.Entry<String, BoneAnimation> entry : clip.bones.entrySet()) {
			if (!firstBone) sb.append(",\n");
			firstBone = false;

			String boneName = entry.getKey();
			BoneAnimation boneAnim = entry.getValue();

			sb.append("    \"").append(boneName).append("\": {\n");

			boolean isTargetBone = boneName.equals(selectedBoneName);
			boolean firstChannel = true;

			if (boneAnim.rotation != null && !boneAnim.rotation.isEmpty()) {
				if (!firstChannel) sb.append(",\n");
				firstChannel = false;

				if (isTargetBone) {
					appendModifiedChannel(sb, "rotation", boneAnim.rotation,
						rotX - initialRotX, rotY - initialRotY, rotZ - initialRotZ,
						new float[] { rotX, rotY, rotZ });
				} else {
					appendChannel(sb, "rotation", boneAnim.rotation);
				}
			}

			if (boneAnim.position != null && !boneAnim.position.isEmpty()) {
				if (!firstChannel) sb.append(",\n");
				firstChannel = false;

				if (isTargetBone) {
					appendModifiedChannel(sb, "position", boneAnim.position,
						posX - initialPosX, posY - initialPosY, posZ - initialPosZ,
						new float[] { posX, posY, posZ });
				} else {
					appendChannel(sb, "position", boneAnim.position);
				}
			}

			if (boneAnim.scale != null && !boneAnim.scale.isEmpty()) {
				if (!firstChannel) sb.append(",\n");
				appendChannel(sb, "scale", boneAnim.scale);
			}

			sb.append("\n    }");
		}

		sb.append("\n  }\n}");
		return sb.toString();
	}

	private static void appendModifiedChannel(StringBuilder sb, String channelName,
			List<Keyframe> keyframes, float dx, float dy, float dz, float[] absoluteValues) {
		if (keyframes.size() == 1) {
			sb.append("      \"").append(channelName).append("\": [")
				.append(formatFloat(absoluteValues[0])).append(", ")
				.append(formatFloat(absoluteValues[1])).append(", ")
				.append(formatFloat(absoluteValues[2])).append("]");
		} else {
			sb.append("      \"").append(channelName).append("\": {\n");
			boolean firstKf = true;
			for (Keyframe kf : keyframes) {
				if (!firstKf) sb.append(",\n");
				firstKf = false;
				sb.append("        \"").append(formatFloat(kf.time)).append("\": [")
					.append(formatFloat(kf.value[0] + dx)).append(", ")
					.append(formatFloat(kf.value[1] + dy)).append(", ")
					.append(formatFloat(kf.value[2] + dz)).append("]");
			}
			sb.append("\n      }");
		}
	}

	private static void appendChannel(StringBuilder sb, String channelName, List<Keyframe> keyframes) {
		if (keyframes.size() == 1) {
			Keyframe kf = keyframes.get(0);
			sb.append("      \"").append(channelName).append("\": [")
				.append(formatFloat(kf.value[0])).append(", ")
				.append(formatFloat(kf.value[1])).append(", ")
				.append(formatFloat(kf.value[2])).append("]");
		} else {
			sb.append("      \"").append(channelName).append("\": {\n");
			boolean firstKf = true;
			for (Keyframe kf : keyframes) {
				if (!firstKf) sb.append(",\n");
				firstKf = false;
				sb.append("        \"").append(formatFloat(kf.time)).append("\": [")
					.append(formatFloat(kf.value[0])).append(", ")
					.append(formatFloat(kf.value[1])).append(", ")
					.append(formatFloat(kf.value[2])).append("]");
			}
			sb.append("\n      }");
		}
	}

	private static String formatFloat(float value) {
		if (value == (int) value) {
			return String.valueOf((int) value);
		}
		return String.format(java.util.Locale.US, "%.4f", value).replaceAll("0+$", "").replaceAll("\\.$", ".0");
	}

	/**
	 * Returns the shooting duration to use: the debug override if active and set,
	 * otherwise the weapon's default.
	 */
	public static int getEffectiveShootingDuration(int weaponDefault) {
		if (active && shootingDurationOverride >= 0) {
			return shootingDurationOverride;
		}
		return weaponDefault;
	}

	/**
	 * Cleans up the debug state: clears offset, marks screen as closed.
	 */
	public static void cleanup() {
		if (currentController != null) {
			currentController.clearDebugOffset();
		}
		screenOpen = false;
	}
}
