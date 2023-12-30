package org.ic3tank;

import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.rusherhack.client.api.events.client.EventUpdate;
import org.rusherhack.client.api.events.render.EventRender2D;
import org.rusherhack.client.api.events.render.EventRender3D;
import org.rusherhack.client.api.feature.module.ModuleCategory;
import org.rusherhack.client.api.feature.module.ToggleableModule;
import org.rusherhack.client.api.utils.ChatUtils;
import org.rusherhack.core.event.subscribe.Subscribe;

/**
 * Example rusherhack module
 *
 * @author John200410
 */
public class SpeedMeasureModule extends ToggleableModule {
	@Nullable
	PositionHolder firstPos = null;
	double lastDistance = 0;
	double lastSpeed = 0;
	
	/**
	 * Constructor
	 */
	public SpeedMeasureModule() {
		super("SpeedMeasure", "Measures Speed over time", ModuleCategory.CLIENT);
	}
	
	/**
	 * 2d renderer demo
	 */
	@Subscribe
	private void onRender2D(EventRender2D event) {

	}
	
	/**
	 * Rotation demo
	 */
	@Subscribe
	private void onUpdate(EventUpdate event) {

	}
	
	//3d renderer demo
	@Subscribe
	private void onRender3D(EventRender3D event) {

	}
	
	@Override
	public void onDisable() {
		lastSpeed = 0;
		lastDistance = 0;
	}

	public void measureStart() {
		if (mc.player == null) return;
		this.firstPos = new PositionHolder(mc.player.getOnPos(), System.currentTimeMillis());
	}

	public void measureEnd() {
		if (mc.player == null) return;
		PositionHolder secondPos = new PositionHolder(mc.player.getOnPos(), System.currentTimeMillis());
		if (this.firstPos == null) return;
		double distance = distanceXZ(this.firstPos, secondPos);
		ChatUtils.print("Distance: %.2f".formatted(distance));
		// Print Time in Hours, Minutes, Seconds
		ChatUtils.print("Time: %s".formatted(convertMillisecondsToHHMMSS(secondPos.time, this.firstPos.time)));
		double blocksPerSecond = distance / (secondPos.time - this.firstPos.time) * 1000;
		double kmPerHour = blocksPerSecond * 3.6;
		lastDistance = distance;
		lastSpeed = blocksPerSecond;
		ChatUtils.print("Speed: %.2f b/s; %.2f km/h".formatted(blocksPerSecond, kmPerHour));
	}

	public double distanceXZ(PositionHolder pos1, PositionHolder pos2) {
		return Math.sqrt(Math.pow(pos1.pos.getX() - pos2.pos.getX(), 2) + Math.pow(pos1.pos.getZ() - pos2.pos.getZ(), 2));
	}

	public void estimate(int distance) {
		if (lastSpeed == 0) {
			ChatUtils.print("No Measures");
			return;
		}

		double estimatedTime = distance / lastSpeed;

		ChatUtils.print("Esitmated Time: %s".formatted(convertMillisecondsToHHMMSS((int) (estimatedTime * 1000), 0)));
	}

	private String convertMillisecondsToHHMMSS(long time1, long time2) {
		long milliseconds = time1 - time2;
		long seconds = milliseconds / 1000;
		long hours = seconds / 3600;
		long minutes = (seconds % 3600) / 60;
		long remainingSeconds = seconds % 60;

		return String.format("%02dh %02dm %02ds", hours, minutes, remainingSeconds);
	}

	static class PositionHolder {
		public BlockPos pos;
		public long time;

		public PositionHolder(BlockPos pos, long time) {
			this.pos = pos;
			this.time = time;
		}
	}
}
