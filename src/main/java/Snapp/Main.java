package Snapp;

class Main
{
    public static void main(String[] args)
    {
        try
        {
            System.out.println(Map.ETA(1,3));

        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        //Savior.loading();
        Driver d = new Driver();
        d.phase1drive();
        //Savior.saving();
        //DB.close();
        return;
    }
}