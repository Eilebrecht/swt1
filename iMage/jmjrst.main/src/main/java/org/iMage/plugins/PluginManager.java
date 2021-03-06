package org.iMage.plugins;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

/**
 * Knows all available plug-ins and is responsible for using the service loader
 * API to detect them.
 *
 */
public final class PluginManager {

	/**
	 * No constructor for utility class.
	 */
	private PluginManager() {
	}

	/**
	 * Return an {@link Iterable} Object with all available
	 * {@link JmjrstPlugin}s sorted accordingly to their priority.
	 * 
	 * @return an {@link Iterable} Object containing all available plug-ins
	 *         sorted according to their priority in ascending order.
	 */
	public static Iterable<JmjrstPlugin> getPlugins() {
		ServiceLoader<JmjrstPlugin> serviceLoader = ServiceLoader.load(JmjrstPlugin.class);

		List<JmjrstPlugin> p = new ArrayList<>();
		for (final JmjrstPlugin plugin : serviceLoader) {
			p.add(plugin);
		}

		return new PluginContainer(p);
	}

}
