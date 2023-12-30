package org.ic3tank;

import org.rusherhack.client.api.RusherHackAPI;
import org.rusherhack.client.api.plugin.Plugin;

/**
 * Example rusherhack plugin
 *
 * @author John200410
 */
public class SpeedMeasurePlugin extends Plugin {
	
	@Override
	public void onLoad() {
		
		//logger
		this.getLogger().info(getName() + " loaded");
		
		//creating and registering a new module
		final SpeedMeasureModule speedMeasureModule = new SpeedMeasureModule();
		RusherHackAPI.getModuleManager().registerFeature(speedMeasureModule);
		
		//creating and registering a new hud element
//		final SpeedMeasureElement speedMeasureElement = new SpeedMeasureElement();
//		RusherHackAPI.getHudManager().registerFeature(speedMeasureElement);
		
		//creating and registering a new command
		final SpeedMeasureCommand speedMeasureCommand = new SpeedMeasureCommand();
		RusherHackAPI.getCommandManager().registerFeature(speedMeasureCommand);
	}
	
	@Override
	public void onUnload() {
		this.getLogger().info(getName() + " unloaded");
	}
	
	@Override
	public String getName() {
		return "Speed Measurer";
	}
	
	@Override
	public String getVersion() {
		return "v1.0";
	}
	
	@Override
	public String getDescription() {
		return "Measures your speed over time";
	}
	
	@Override
	public String[] getAuthors() {
		return new String[]{"Ic3Tank"};
	}
}
