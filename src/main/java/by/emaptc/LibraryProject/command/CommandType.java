package by.emaptc.LibraryProject.command;

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
    EDIT_RATE(new ChangeRateCommand()),
    DELETE_USER(new DeleteTaxiCommand()),
    RATE_HISTORY(new RateHistoryCommand()),
    ADD_MODERATOR(new AddModeratorCommand()),

    /**
     * Common commands.
     */
    COMMON_LOGIN(new LoginCommand()),
    COMMON_LOGOUT(new LogoutCommand()),
    COMMON_REGISTER(new RegisterCommand()),
    COMMON_CHANGE_LANGUAGE(new ChangeLanguageCommand()),

    /**
     * Moderator Command's commands.
     */
    DISPATCHER(new DispatcherCommand()),
    ACCEPT(new AcceptCommand()),
    HISTORY(new HistoryCommand()),
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
