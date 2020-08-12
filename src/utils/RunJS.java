package utils;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class RunJS {

	public static String run(String jsCode) {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");
		try {
			engine.eval("function add(a,b){" + "return a+b;" + "}");
			if (engine instanceof Invocable) {
				Invocable in = (Invocable) engine;
				System.out.println(in.invokeFunction("add", 1, 1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "void";
	}
}
