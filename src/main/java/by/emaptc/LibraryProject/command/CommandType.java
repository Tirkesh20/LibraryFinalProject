package by.emaptc.LibraryProject.command;

import by.emaptc.LibraryProject.command.adminCommand.ChangeBehaviorCommand;
import by.emaptc.LibraryProject.command.adminCommand.DeleteUserCommand;
import by.emaptc.LibraryProject.command.adminCommand.ShowUserCommand;
import by.emaptc.LibraryProject.command.authCommand.LogOutCommand;
import by.emaptc.LibraryProject.command.authCommand.LoginCommand;
import by.emaptc.LibraryProject.command.authCommand.RegisterCommand;
import by.emaptc.LibraryProject.command.common.LanguageCommand;

import java.util.HashMap;
import java.util.Map;

public enum CommandType {
    /**
     * needs database.
     *
     *
     * <p>
     * Admin commands.
     */
    SHOW_User(new ShowUserCommand()),
    EDIT_RATE(new ChangeBehaviorCommand()),
    DELETE_USER(new DeleteUserCommand()),

//    /**
//     * Common commands.
//     */
      COMMON_LOGIN(new LoginCommand()),
      COMMON_LOGOUT(new LogOutCommand()),
      COMMON_REGISTER(new RegisterCommand()),
      COMMON_CHANGE_LANGUAGE(new LanguageCommand()),

    /**
     * Client Command
     */
    BOOK_ORDER(new OrderCommand()),
    CANCEL_ORDER(new ClientCancelOrderCommand());


    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCurrentCommand() {
        return command;
    }

    private static final Map<String, CommandType> lookup = new HashMap<>();

    static {
        for (CommandType env : CommandType.values()) {
            lookup.put(env.toString(), env);
        }
    }

    public static CommandType getCommand(String type) {
        return lookup.get(type);
    }
}
