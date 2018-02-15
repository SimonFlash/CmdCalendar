package com.mcsimonflash.sponge.cmdscheduler.command;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.mcsimonflash.sponge.cmdcontrol.teslalibs.command.*;
import com.mcsimonflash.sponge.cmdcontrol.teslalibs.command.arguments.Arguments;
import com.mcsimonflash.sponge.cmdscheduler.CmdScheduler;
import com.mcsimonflash.sponge.cmdscheduler.internal.Config;
import com.mcsimonflash.sponge.cmdscheduler.task.AdvancedTask;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.text.Text;

@Singleton
@Aliases("start")
@Permission("cmdscheduler.command.start.base")
public class Start extends Command {

    @Inject
    private Start(CommandService service) {
        super(service, Settings.create().arguments(
                Arguments.choices(Config.tasks, ImmutableMap.of("no-choice", "Input <arg> is not the name of a task!")).toElement("task")
        ));
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        AdvancedTask task = args.<AdvancedTask>getOne("task").get();
        task.start(CmdScheduler.get().Container);
        src.sendMessage(Text.of("Successfully started task " + task.getName() + "."));
        return CommandResult.success();
    }

}