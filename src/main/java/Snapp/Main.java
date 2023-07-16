package Snapp;

class Main
{
    public static void main(String[] args)
    {
        //Savior.loading();
        Driver d = new Driver();
        d.phase1drive();
        //Savior.saving();
        DB.close();
        return;
    }
}