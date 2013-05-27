package jp.keik.alertmonitor.domain;

import java.util.HashMap;
import java.util.Map;

public final class Constants {

	public static final Map<Integer, String> PRIORITY = new HashMap<Integer, String>() {
		private static final long serialVersionUID = 1L;
		{
			put(1, "Low");
			put(2, "Medium");
			put(3, "High");
		}
	};

	public static final Map<String, String> SPOT = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("network", "通信");
			put("device", "装置");
		}
	};

	public static final Map<String, String> TYPE = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("err01", "ERR01");
			put("err02", "ERR02");
			put("err03", "ERR03");
		}
	};

}
