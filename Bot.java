import java.io.File;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.utils.IOUtils;

public class Bot {
	private static final boolean TRACE_MODE = false;
	static String BotName = "super";
			
	public static void main(String[] args) {
		try {
			
			String resourcesPath = getResourcesPath();
			System.out.println(resourcesPath);
			MagicBooleans.trace_mode = TRACE_MODE;
			Bot bot = new Bot("Super", resourcesPath);
			Chat chatSession = new Chat(bot);
			bot.brain.nodeStats();
			String textLine = "";
			
			while (true) {
				System.out.print("Human ; ");
				textLine = IOUtils.readInputTextLine();
				if ((textLine == null)) || (textLine.length() < 1)
					textLine = MagicStrings.null_input;
				if (textLine.equals("q")) {
					System.exit(0);
				} else if (textLine.equals("wq")) {
					bot.writeQuit();
					System,exit(0);
				} else {
					String request = textLine;
					if (MagicBooleans.trace_mode)
						System.out.println("STATE=" + request + ":THAT=" + ((String) ((Object) chatSession.thatHistory).get(0).get(0) + ":TOPIC=" + chatSession.predicates.get("topic"));
						String response = chatSession.multisentenceRespond(request);
						while (response.contains("&lt;"))
							response = response.replace("&lt;", "<");
						while (response.contains("&gt;"))
							response = response.replace("&gt;", ">");
						System.out.println("Robot : " + response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void writeQuit() {
		// TODO Auto-generated method stub
		
	}

	private static String getResourcesPath() {
		// TODO Auto-generated method stub
		return null;
	}
	
}