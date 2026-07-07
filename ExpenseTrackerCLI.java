public class ExpenseTrackerCLI {
    public static void main(String[] args) {
        ExpenseManager manager = new ExpenseManager();
        if (args.length < 1) {
            System.out.println("Usage: ExpenseTrackerCLI <command> [arguments] ");
            return;
        }
        String command = args[0];
        switch (command) {
            case "add":
                String description = "";
                double amount = 0;
                boolean hasDescription = false;
                boolean hasAmount = false;
                for (int i = 1; i < args.length; i++) {
                    if (args[i].equals("--description")) {
                        if (i + 1 < args.length && !args[i + 1].startsWith("--")) {
                            description = args[i + 1];
                            hasDescription = true;
                            i++;
                        } else {
                            System.out.println("Error, description do not empty! ");
                            return;
                        }
                    } else if (args[i].equals("--amount")) {
                        if (i + 1 < args.length && !args[i + 1].startsWith("--")) {
                            amount = Double.parseDouble(args[i + 1]);
                            hasAmount = true;
                            i++;
                        } else {
                            System.out.println("Error, amount do not empty! ");
                            return;
                        }
                    }
                }
                if (!hasAmount || !hasDescription) {
                    System.out.println("Error, usage : ExpenseTrackerCLI <add> --description \" \" --amount \" \"");
                    return;
                }
                manager.addExpense(description, amount);
                ExpenseManager.saveExpense();
                return;

            case "list":
                manager.listExpense();
                return;
            case "summary":
                for (int i = 1; i < args.length; i++) {
                    if (args[i].equals("--month")) {
                        if (i + 1 < args.length && args[i + 1] != null) {
                            manager.summaryExpenseByMonth(Integer.parseInt(args[i + 1]));
                            return;
                        } else {
                            System.out.println("you must add number of month after flag \"--month\"");
                            return;
                        }
                    }
                }
                manager.summaryExpense();
                return;
            case "delete":
                for (int i = 1; i < args.length; i++) {
                    if (args[i].equals("--id")) {
                        if (i + 1 < args.length && args[i + 1] != null) {
                            manager.deleteExpense(Integer.parseInt(args[i + 1]));
                            return;
                        } else {
                            System.out.println("you must add id of expense after flag \"--id\"");
                            return;
                        }
                    }
                }
                System.out.println("you must add flage \"--id\" after commad delete");
                return;
            case "update":
                int updateId = -1;
                String newDescription = "";
                double newAmount = 0;
                boolean newHasId = false;
                boolean newHasDescription = false;
                boolean newHasAmount = false;
                for (int i = 1; i < args.length; i++) {
                    if (args[i].equals("--id")) {
                        if (i + 1 < args.length && !args[i + 1].startsWith("--")) {
                            try {
                                updateId = Integer.parseInt(args[i + 1]);
                                newHasId = true;
                                i++;
                            } catch (NumberFormatException e) {
                                System.out.println("Error: ID must be a number!");
                                return;
                            }
                        } else {
                            System.out.println("Error, id do not empty! ");
                            return;
                        }
                    } else if (args[i].equals("--description")) {
                        if (i + 1 < args.length && !args[i + 1].startsWith("--")) {
                            newDescription = args[i + 1];
                            newHasDescription = true;
                            i++;
                        } else {
                            System.out.println("Error, description do not empty! ");
                            return;
                        }
                    } else if (args[i].equals("--amount")) {
                        if (i + 1 < args.length && !args[i + 1].startsWith("--")) {
                            newAmount = Double.parseDouble(args[i + 1]);
                            newHasAmount = true;
                            i++;
                        } else {
                            System.out.println("Error, amount do not empty! ");
                            return;
                        }
                    }
                }
                if (!newHasId || !newHasAmount || !newHasDescription) {
                    System.out.println("Error, usage : ExpenseTrackerCLI update --id <id> --description \" \" --amount \" \"");
                    return;
                }
                manager.updateExpense(updateId, newDescription, newAmount);
                return;
            default:
                System.out.print("Usage: java ExpenseTrackerCLI <command> [arguments]");
                return;
        }

    }
}