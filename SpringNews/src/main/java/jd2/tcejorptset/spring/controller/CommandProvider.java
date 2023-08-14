package jd2.tcejorptset.spring.controller;

import java.util.HashMap;
import java.util.Map;

import jd2.tcejorptset.spring.bean.ErrorCode;
import jd2.tcejorptset.spring.controller.impl.DoAddNews;
import jd2.tcejorptset.spring.controller.impl.DoChangeLocale;
import jd2.tcejorptset.spring.controller.impl.DoDeleteNews;
import jd2.tcejorptset.spring.controller.impl.DoEditNews;
import jd2.tcejorptset.spring.controller.impl.DoRegistration;
import jd2.tcejorptset.spring.controller.impl.DoSignIn;
import jd2.tcejorptset.spring.controller.impl.DoSignOut;
import jd2.tcejorptset.spring.controller.impl.GoToAddEditNewsPage;
import jd2.tcejorptset.spring.controller.impl.GoToBasePage;
import jd2.tcejorptset.spring.controller.impl.GoToErrorPage;
import jd2.tcejorptset.spring.controller.impl.GoToNewsList;
import jd2.tcejorptset.spring.controller.impl.GoToViewNews;

@Deprecated
public final class CommandProvider {
	private static final CommandProvider instance = new CommandProvider();
	
	private final Map<CommandName, Command> commands = new HashMap<>();
	
	private CommandProvider() {
		commands.put(CommandName.GO_TO_BASE_PAGE, new GoToBasePage());
		commands.put(CommandName.GO_TO_NEWS_LIST, new GoToNewsList());
		commands.put(CommandName.GO_TO_VIEW_NEWS, new GoToViewNews());
		commands.put(CommandName.GO_TO_ADD_EDIT_NEWS_PAGE, new GoToAddEditNewsPage());
		commands.put(CommandName.GO_TO_ERROR_PAGE, new GoToErrorPage());
		commands.put(CommandName.DO_SIGN_IN, new DoSignIn());
		commands.put(CommandName.DO_SIGN_OUT, new DoSignOut());
		commands.put(CommandName.DO_REGISTRATION, new DoRegistration());
		commands.put(CommandName.DO_CHANGE_LOCALE, new DoChangeLocale());
		commands.put(CommandName.DO_ADD_NEWS, new DoAddNews());
		commands.put(CommandName.DO_EDIT_NEWS, new DoEditNews());
		commands.put(CommandName.DO_DELETE_NEWS, new DoDeleteNews());
	}
	
	public static CommandProvider getInstance () {
		return instance;
	}
	
	public final Command getCommand(String name) {
		try {
		CommandName  commandName = CommandName.valueOf(name.toUpperCase());
		Command command = commands.get(commandName);
		return command;
		}
		catch (Exception e) {
			e.printStackTrace();
			return new GoToErrorPage(ErrorCode.ERROR_404.name().toLowerCase());
		}
	}

}
