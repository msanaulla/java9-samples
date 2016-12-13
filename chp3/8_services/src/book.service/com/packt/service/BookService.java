package com.packt.service;

import com.packt.model.Book;
import com.packt.spi.BookServiceProvider;
import java.io.Closeable;
import java.util.Iterator;
import java.util.ServiceLoader;

public abstract class BookService{
	public abstract void create(Book book);
	public abstract Book read(String id);
	public abstract void update(Book book);
	public abstract void delete(String id);

	public static BookService getInstance(){
		ServiceLoader<BookServiceProvider> sl
            = ServiceLoader.load(BookServiceProvider.class);
        Iterator<BookServiceProvider> iter = sl.iterator();
        if (!iter.hasNext())
            throw new RuntimeException("No service providers found!");
        while(iter.hasNext()){
        	BookServiceProvider provider = iter.next();
        	System.out.println(provider.getClass());
        }
        BookServiceProvider provider = iter.next();
        return provider.getBookService();
	}
}