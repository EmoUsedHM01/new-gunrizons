package com.gtnewhorizon.newgunrizons.registry;

/**
 * Registry for weapon attachments (scopes, grips, silencers, etc.).
 * <p>
 * Attachments are defined here and referenced by weapon factories
 * via {@code withCompatibleAttachment(attachment, "boneName")}.
 */
public class Attachments {

	public static void init() {
		// Attachments will be defined here using AttachmentBuilder
		// and bone-based positioning on weapon models.
	}
}
