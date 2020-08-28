package com.dzx.watch.dir;

import java.nio.file.Path;
import java.nio.file.WatchEvent.Kind;

public interface WatcherResultHandler {
	public void handleResult(Path path, Kind<?> event);
}
