package com.example.todo;

import java.util.ArrayList;
import java.util.Scanner;

public class TodoApp {

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("命令：add <标题> | list | done <id> | delete <id> | quit");

        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            int space = line.indexOf(' ');
            String command = space < 0 ? line : line.substring(0, space);
            String argument = space < 0 ? "" : line.substring(space + 1).trim();

            switch (command) {
                case "add":
                    if (argument.isEmpty()) {
                        System.out.println("标题不能为空，用法：add <标题>");
                    } else {
                        Task added = new Task(argument);
                        tasks.add(added);
                        System.out.println("已添加 [" + added.id + "]：" + added.title);
                    }
                    break;
                case "list":
                    if (tasks.isEmpty()) {
                        System.out.println("暂无任务");
                    } else {
                        for (Task task : tasks) {
                            System.out.println(task);
                        }
                    }
                    break;
                case "done": {
                    Task task = findById(tasks, argument);
                    if (task != null) {
                        task.completed = true;
                        System.out.println("已完成：" + task.title);
                    }
                    break;
                }
                case "delete": {
                    Task task = findById(tasks, argument);
                    if (task != null) {
                        tasks.remove(task);
                        System.out.println("已删除：" + task.title);
                    }
                    break;
                }
                case "quit":
                    System.out.println("再见");
                    return;
                default:
                    System.out.println("未知命令：" + command);
            }
        }
    }

    private static Task findById(ArrayList<Task> tasks, String argument) {
        int id;
        try {
            id = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            System.out.println("ID 必须是数字，用法：done <id> / delete <id>");
            return null;
        }
        for (Task task : tasks) {
            if (task.id == id) {
                return task;
            }
        }
        System.out.println("找不到 ID 为 " + id + " 的任务");
        return null;
    }
}

class Task {
    private static int counter = 0;

    int id;
    String title;
    boolean completed;

    Task(String title) {
        this.id = ++counter;
        this.title = title;
        this.completed = false;
    }

    @Override
    public String toString() {
        return id + ". [" + (completed ? "V" : " ") + "] " + title;
    }
}
