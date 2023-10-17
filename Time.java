public class Time { //Time class
    private int timeHours; //private variable to store Hours
    private int timeMins; //private variable to store Minutes
    private int timeSecs; //private variable to store Seconds

    public Time() { //default constructor, uses 0 for everything (midnight)
        this.timeHours = 0;
        this.timeMins = 0;
        this.timeSecs = 0;
    }

    public Time(int timeHours, int timeMins, int timeSecs) { //int constructor, takes in hours, mins, secs (in that order) as integers and assigns them to the object
        this.timeHours = timeHours;
        this.timeMins = timeMins;
        this.timeSecs = timeSecs;
    }

    public static Time fromString(String inString) { //fromString method/constructor, takes in String of specified style and returns Time object
        String[] stringTime = inString.split(":"); //split up input string by ':' and store in Array of strings
        int[] intTime = new int[3]; //establish a size 3 array of int to hold time
        //parse to Int and assign intTime with stringTime's contents, in order
        intTime[0] = Integer.parseInt(stringTime[0]);
        intTime[1] = Integer.parseInt(stringTime[1]);
        intTime[2] = Integer.parseInt(stringTime[2]);
        return new Time(intTime[0], intTime[1], intTime[2]); //using int constructor return Time object with Hour, Min, and Sec from the int array
    }

    public void print(boolean military) { //print method, takes in boolean to determine if military time
        this.increment(0); //run increment (with no addition) to make sure all numbers 'waterfall' down to below 60 before printing (if secs or mins get assigned to a number larger than 59 upon creation)
        if (military) { //if it's in military time, we don't have to worry about any formatting and can just print
            System.out.println(this.timeHours + ":" + String.format("%02d",this.timeMins) + ":" + String.format("%02d",this.timeSecs)); //print time in military style, using String formatting to ensure that each spot has 2 digits (01 instead of 1, etc)
        } else if (this.timeHours == 0) {//for the 12AM hour (from 12:00:00am to 12:59:59am)
            System.out.println("12:" + String.format("%02d",this.timeMins) + ":" + String.format("%02d",this.timeSecs) + " AM");
        } else if (this.timeHours == 12) {//for the 12PM hour (from 12:00:00pm to 12:59:59pm)
            System.out.println("12:" + String.format("%02d",this.timeMins) + ":" + String.format("%02d",this.timeSecs) + " PM");
        } else if (this.timeHours < 12) {//from 1:00:00 am to 11:59:59 am
            System.out.println(this.timeHours + ":" + String.format("%02d",this.timeMins) + ":" + String.format("%02d",this.timeSecs) + " AM");
        } else { //from 1:00:00pm to 11:59:59 pm
            this.timeHours -= 12; //adjust for afternoon 12 hr shift (1pm instead of 13 pm, etc)
            System.out.println(this.timeHours + ":" + String.format("%02d",this.timeMins) + ":" + String.format("%02d",this.timeSecs) + " PM");
        }
    }

    public void increment(int seconds) { //increment method
        this.timeSecs += (seconds); //add input seconds to existing seconds
        if (timeSecs >= 60) { //if that put us over 59 seconds (most likely)
            this.timeMins += (this.timeSecs / 60); //add any overflow minutes to existing minutes, 'carry the minute'
            this.timeSecs %= 60; //set seconds to the remainder (will only ever be 0-59)
        }
        if (timeMins >= 60) { //if that put us over 59 minutes
            this.timeHours += (this.timeMins / 60); //add any overflow hours to existing hours, 'carry the hours'
            this.timeMins %= 60; //set minutes to the remainder (will only ever be 0-59)
        }
        //there isn't any instruction as to what to do if hours goes above 23 (there's no days spot, so im gonna hope its not a case we have to worry about)
    }
}
