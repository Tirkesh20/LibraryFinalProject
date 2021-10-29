package by.emaptc.LibraryProject.controller.command;

import by.emaptc.LibraryProject.controller.command.implementation.adminCommand.ChangeBehaviorCommand;
import by.emaptc.LibraryProject.controller.command.implementation.adminCommand.AddLiteratureCommand;
import by.emaptc.LibraryProject.controller.command.implementation.userCommand.*;
import by.emaptc.LibraryProject.controller.command.implementation.adminCommand.DeleteUserCommand;
import by.emaptc.LibraryProject.controller.command.implementation.common.ShowLiteraturesCommand;
import by.emaptc.LibraryProject.controller.command.implementation.adminCommand.ShowUserCommand;
import by.emaptc.LibraryProject.controller.command.implementation.authCommand.RegisterCommand;
import by.emaptc.LibraryProject.controller.command.implementation.authCommand.LogOutCommand;
import by.emaptc.LibraryProject.controller.command.implementation.authCommand.LoginCommand;
import by.emaptc.LibraryProject.controller.command.implementation.common.LanguageCommand;

import java.util.HashMap;
import java.util.Map;

public enum CommandType {
    /**
     * needs database.
     *
     * <p>
     * Admin commands.
     */
    SHOW_User(new ShowUserCommand()),
    EDIT_RATE(new ChangeBehaviorCommand()),
    ADD_LITERATURE(new AddLiteratureCommand()),
    DELETE_USER(new DeleteUserCommand()),
    /**
     * Common commands.
     */
    LOGIN(new LoginCommand()),
    LOGOUT(new LogOutCommand()),
    COMMON_REGISTER(new RegisterCommand()),
    COMMON_LITERATURES(new ShowLiteraturesCommand()),
    COMMON_CHANGE_LANGUAGE(new LanguageCommand()),
    /**
     * Client Command
     */

    CONFIRM_ISSUE_LITERATURE(new IssueConfirmCommand()),
    COMMON_USER_LITERATURES(new ShowUserBooksCommand()),
    ISSUE_LITERATURE(new IssueCommand()),
    ADD_FEEDBACK(new AddFeedbackCommand()),
    COMMON_RETURN_ISSUE(new ReturnIssueCommand());

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
