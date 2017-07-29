package cn.tedu.cloud_note.util;

public class NoteException extends RuntimeException{
	//spring事务管理时,
	//只有遇到RuntimeException时才回滚
	public NoteException(String msg,Throwable t){
		super(msg,t);
	}
}
