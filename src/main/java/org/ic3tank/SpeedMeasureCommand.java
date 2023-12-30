package org.ic3tank;

import org.rusherhack.client.api.RusherHackAPI;
import org.rusherhack.client.api.feature.command.Command;
import org.rusherhack.core.command.annotations.CommandExecutor;

/**
 * Example rusherhack command
 *
 * @author John200410
 */
public class SpeedMeasureCommand extends Command {
	public SpeedMeasureCommand() {
		super("SpeedMeasure", "Measures Speed");
	}
	
	/**
	 * base command that takes in no arguments
	 */
	@CommandExecutor
	private String example() {
		//when return type is String you return the message you want to return to the user
		return "Hello World!";
	}

	@CommandExecutor(subCommand = "start")
	private String startMeasure() {
		RusherHackAPI.getModuleManager().getFeature("SpeedMeasure").ifPresent(speedMeasureModule -> {
			if (speedMeasureModule instanceof SpeedMeasureModule speedMeasureModule1) {
				speedMeasureModule1.measureStart();
			}
		});
		return "Started measuring";
	}

	@CommandExecutor(subCommand = "end")
	private void endMeasure() {
		RusherHackAPI.getModuleManager().getFeature("SpeedMeasure").ifPresent(speedMeasureModule -> {
			if (speedMeasureModule instanceof SpeedMeasureModule speedMeasureModule1) {
				speedMeasureModule1.measureEnd();
			}
		});
	}

	@CommandExecutor(subCommand = "estimate")
	@CommandExecutor.Argument("int")
	private void estimate(int distance) {
		RusherHackAPI.getModuleManager().getFeature("SpeedMeasure").ifPresent(speedMeasureModule -> {
			if (speedMeasureModule instanceof SpeedMeasureModule speedMeasureModule1) {
				speedMeasureModule1.estimate(distance);
			}
		});
	}
}
