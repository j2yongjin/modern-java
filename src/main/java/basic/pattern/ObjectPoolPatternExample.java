package basic.pattern;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Created by yjlee on 2018-10-14.
 */
public class ObjectPoolPatternExample {

    static abstract class ObjectPool<T>{
        private long expirationTime;

        private Hashtable<T,Long> locked ,unlocked;
        public ObjectPool(){
            expirationTime = 3000L;
            locked = new Hashtable<>();
            unlocked = new Hashtable<>();
        }

        protected abstract T create();
        public abstract boolean validate(T o) throws SQLException;
        public abstract void expire(T o) throws SQLException;

        public synchronized T checkout() throws SQLException {
            long now = System.currentTimeMillis();

            T t;
            if(unlocked.size() >0){
                Enumeration<T> e = unlocked.keys();
                while (e.hasMoreElements()){
                    t = e.nextElement();
                    if( (now-unlocked.get(t)) > expirationTime){
                        unlocked.remove(t);
                        expire(t);
                        t= null;
                    }else{
                        if(validate(t)){
                            unlocked.remove(t);
                            locked.put(t,now);
                            return t;
                        }else{
                            unlocked.remove(t);
                            expire(t);
                            t = null;
                        }
                    }
                }
            }

            t = create();
            locked.put(t,now);
            return t;
        }

        public synchronized void checkin(T t){
            locked.remove(t);
            unlocked.put(t,System.currentTimeMillis());
        }
    }

    static class JdbcConnectionPool extends ObjectPool<Connection>{

        String dsn,user,password;

        public JdbcConnectionPool(String driver,String dsn, String user, String password) {
            super();
            try{
                Class.forName(driver).newInstance();
            }catch (Exception e){

            }
            this.dsn = dsn;
            this.user = user;
            this.password = password;
        }

        @Override
        protected Connection create() {

            try {
                return    DriverManager.getConnection(dsn,user,password);
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }


        }

        @Override
        public boolean validate(Connection o) throws SQLException {
            return !o.isClosed();
        }

        @Override
        public void expire(Connection o) throws SQLException {
            o.close();
        }
    }

    public static void main(String[] args) throws SQLException {
        JdbcConnectionPool jdbcConnectionPool = new JdbcConnectionPool("org.hsqldb.jdbcDriver", "jdbc:hsqldb://localhost/mydb",
                "sa", "secret");
        //GET CONNECTION
        Connection connection = jdbcConnectionPool.checkout();

        //exec

        // Connection Release
        jdbcConnectionPool.checkin(connection);

    }

}
