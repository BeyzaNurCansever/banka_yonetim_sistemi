
public class deadlock_bilgi {
   private int işlem_no1;
   private int işlemno_2;
   private int deadlock_no;

    public deadlock_bilgi(int işlem_no1, int işlemno_2, int deadlock_no) {
        this.işlem_no1 = işlem_no1;
        this.işlemno_2 = işlemno_2;
        this.deadlock_no = deadlock_no;
    }

    public int getIşlem_no1() {
        return işlem_no1;
    }

    public void setIşlem_no1(int işlem_no1) {
        this.işlem_no1 = işlem_no1;
    }

    public int getIşlemno_2() {
        return işlemno_2;
    }

    public void setIşlemno_2(int işlemno_2) {
        this.işlemno_2 = işlemno_2;
    }

    public int getDeadlock_no() {
        return deadlock_no;
    }

    public void setDeadlock_no(int deadlock_no) {
        this.deadlock_no = deadlock_no;
    }
   
}
