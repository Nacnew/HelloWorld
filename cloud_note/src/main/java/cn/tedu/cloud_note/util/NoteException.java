package cn.tedu.cloud_note.util;

public class NoteException extends RuntimeException{
	//spring�������ʱ,
	//ֻ������RuntimeExceptionʱ�Żع�
	public NoteException(String msg,Throwable t){
		super(msg,t);
	}
}
