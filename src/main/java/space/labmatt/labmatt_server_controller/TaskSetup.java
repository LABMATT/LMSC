package space.labmatt.labmatt_server_controller;

class TaskSetup {

    private LABMATT_SERVER_CONTROLLER plugin;

    TaskSetup(LABMATT_SERVER_CONTROLLER labmatt_server_controller) {
        this.plugin = labmatt_server_controller;
    }

    //Registers tasks to be carried out by LMSC
    void TaskReg() {
        //Cleans up LMSC every 1 minute 30 seconds.
        //LMSCCleanupTask clean = new LMSCCleanupTask(this);
        //clean.runTaskTimer(this, 0, 1560);
    }
}
