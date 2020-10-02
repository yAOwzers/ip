# User Guide
Duke is a Command Line Interface (CLI) desktop application that keeps track of a user's task list. 

## Table of Contents
* [Getting Started](#getting-started)
* [Feature](#features)
  * [ToDo](#todo)
  * [Deadline](#deadline)
  * [Event](#event)
  * [Delete](#delete)
  * [List](#list)
  * [Find](#find) 
  * [Mark as Done](#mark-as-done) 
  * [Exit](#exit)
  * [Save](#save)
* [Command Summary](#command-summary)

## Getting Started
1. Ensure that you have Java `11` or above installed on your device.
2. Download the latest `duke.jar` file from: https://github.com/yAOwzers/ip/releases/tag/A-UserGuide
3. Copy the downloaded `duke.jar` file to your desired folder as the home folder for your program.
4. Launch the application by entering `jar -jar ip.jar` to run the program. 
5. A welcome message should appear as displayed below:

```
Hello from
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|
HOI there! I'm Duke
What can I do for you?
-------------------------------------------------------------
```

## Features 
Words in `UPPER_CASE` are the parameters that are by the user.\
eg. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo math assignment`.

### **`todo`** 
Adds a todo task to the task list. A todo task is a task which requires a description.

Format: `todo DESCRIPTION`

Example: `todo math assignment`

Expected Outcome:
```
Got it. I've added this task:
  [T][ ] math assignment
Now you have 1 task in the list.
-------------------------------------------------------------
```

### **`deadline`**
Adds a deadline task to the task list. A deadline task is a task which requires a description as well as a date and time.
A `/by` command is placed before the date and time. The format of the date and time is as follows: YYYY-MM-DD hhmm.

Format: `deadline DESCRIPTION /by DATE TIME`

Example: `deadline math assignment /by 2020-10-10 2000`

Expected Outcome: 
```
Got it. I've added this task:
  [D][ ] math assignment (by: 10 October 2020 08:00 PM)
Now you have 1 task in the list.
-------------------------------------------------------------
```

### **`event`**
Adds an event task to the task list. An event task is a task which requires a description as well as a date and time.
A `/at` command is placed before the date and time. The format of the date and time is as follows: YYYY-MM-DD hhmm.

Format: `event DESCRIPTION /by DATE START`

Example: `event project meeting /at 2020-10-10 1900`

Expected Outcome: 
```
Got it. I've added this task:
  [E][ ] project meeting (at: 10 October 2020 07:00 PM)
Now you have 2 tasks in the list.
-------------------------------------------------------------
```

### **`list`**
Lists down all the existing task(s) in the task list.

Format: `list`

Expected Outcome: 
```
Here are the tasks in your list:
1. [E][ ] project meeting (at: 10 October 2020 07:00 PM)
2. [T][ ] buy milk
3. [D][ ] math assignment (by: 10 October 2020 08:00 PM)
-------------------------------------------------------------
```

### **`find`**
Lists down all the existing tasks that matches the keyword provided by the user from the task list.

Format: `find KEYWORD`

Example: `find meeting`

Expected Outcome: 
```
Here are the tasks in your list that matches 'meeting':
1.[E][ ] project meeting (at: 10 October 2020 07:00 PM)
-------------------------------------------------------------
```

### **`done`**
Marks a task in the task list as done. This is indicated by a '\*' symbol inside the second [].
The index following the `done` command refers to the index of the task in the current task list.

Format: `done INDEX`

Example: `done 2`

Expected Outcome: 
```
NOICE! I've marked the following task as done:
  [T][*] buy milk
-------------------------------------------------------------
```

### **`delete`**
Deletes an existing task from the task list. The index following the `delete` command refers to the index of the task in the current task list.

Format: `delete INDEX`

Example: `delete 2`

Expected Outcome: 
```
Okay sure, the following task has been deleted from your list:
  [T][*] buy milk
Now you have 2 tasks in the list.
-------------------------------------------------------------
```

### **`bye`**
Exits the application.

Format: `bye`

Expected Outcome: 
```
bye
GuuuudBYE. Hope to see you again soon!
```

### Save 
Data involved in this application is saved into the hard disk via the `data.txt` file after any command that alters the data is executed automatically. 

## Command Summary

| Action | Format | Examples |
| ------------- | ------------- |------------- |
| Add ToDo | `todo DESCRIPTION` | `todo math assignment` |
| Add deadline | `deadline DESCRIPTION /by DATE TIME` | `deadline math assignment /by 2020-10-10 2000` |
| Add event | `event DESCRIPTION /AT DATE START_TIME-END_TIME` | `event project meeting /at 2020-10-10 1900` |
| View list | `list` | -
| Delete | `delete INDEX` | `delete 2` |
| Mark done | `done INDEX` | `done 2` |
| Find tasks (keyword)  | `find KEYWORD` | `find meeting` |
| Exit | `bye` | - |

