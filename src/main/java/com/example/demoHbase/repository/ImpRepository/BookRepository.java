package com.example.demoHbase.repository.ImpRepository;

import com.example.demoHbase.model.Book;
import com.example.demoHbase.repository.ConfHbase;
import com.example.demoHbase.repository.IRepository.IBookRepository;
import lombok.SneakyThrows;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository implements IBookRepository {



    @SneakyThrows
    @Override
    public Long addBookToHbase(Book book) {
        Configuration config = ConfHbase.getconfig();
        Table table=null;
        Connection connection =ConnectionFactory.createConnection(config);
        try {
            table = connection.getTable(TableName.valueOf("lib_book"));
            Put p = new Put(Bytes.toBytes(book.getId()));
            p.addColumn(Bytes.toBytes("book"),
                    Bytes.toBytes("name"),
                    Bytes.toBytes(book.getName()));

            p.addColumn(Bytes.toBytes("book"),
                    Bytes.toBytes("page"),
                    Bytes.toBytes(book.getPage()));

            p.addColumn(Bytes.toBytes("book"),
                    Bytes.toBytes("publish"),
                    Bytes.toBytes(book.getPublish()));

            p.addColumn(Bytes.toBytes("author"),
                    Bytes.toBytes("nameAuthor"),
                    Bytes.toBytes(book.getNameAuthor()));

            p.addColumn(Bytes.toBytes("book"),
                    Bytes.toBytes("description"),
                    Bytes.toBytes(book.getDescription()));

            p.addColumn(Bytes.toBytes("Book"),
                    Bytes.toBytes("edit"),
                    Bytes.toBytes(book.getEdit()));

            table.put(p);


        } catch (IOException e) {
            e.printStackTrace();
            return Long.valueOf(0);
        }finally {
            if(table!=null){
                table.close();
            }
            if (connection!=null){
                connection.close();
            }
        }
        return book.getId();
    }

    @Override
    public Long updateBookToHbase(Book book) {
        return addBookToHbase(book);
    }

    @SneakyThrows
    @Override
    public Long deleteBookToHbase(Book book) {
        Configuration config = ConfHbase.getconfig();
        Table table=null;
        Connection connection =ConnectionFactory.createConnection(config);
        try {
            table = connection.getTable(TableName.valueOf("lib_book"));
            Delete delete = new Delete(Bytes.toBytes(book.getId()));
            delete.addFamily(Bytes.toBytes("book"));
            delete.addFamily(Bytes.toBytes("author"));
            table.delete(delete);

        }catch (IOException e) {
            e.printStackTrace();
            return Long.valueOf(0);
        }finally {
            if(table!=null){
                table.close();
            }
            if (connection!=null){
                connection.close();
            }
        }
        return book.getId();
    }

    @SneakyThrows
    @Override
    public List<Book> getAllBookToHbase() {
        Configuration config = ConfHbase.getconfig();
        Table table=null;
        List<Book> books=new ArrayList<>();
        Connection connection =ConnectionFactory.createConnection(config);
        try {
            table = connection.getTable(TableName.valueOf("lib_book"));
            Scan scan = new Scan();
            scan.addColumn(Bytes.toBytes("book"),Bytes.toBytes("name"));
            scan.addColumn(Bytes.toBytes("book"),Bytes.toBytes("page"));
            scan.addColumn(Bytes.toBytes("book"),Bytes.toBytes("publish"));
            scan.addColumn(Bytes.toBytes("book"),Bytes.toBytes("nameAuthor"));
            scan.addColumn(Bytes.toBytes("book"),Bytes.toBytes("description"));
            scan.addColumn(Bytes.toBytes("book"),Bytes.toBytes("edit"));

            ResultScanner results=table.getScanner(scan);
            for (Result result = results.next(); result != null; result = results.next()){
                Long key_row=Bytes.toLong(result.getRow());
                String name =Bytes.toString(result.getValue(Bytes.toBytes("book"),Bytes.toBytes("name")));
                String publish =Bytes.toString(result.getValue(Bytes.toBytes("book"),Bytes.toBytes("publish")));
                String description =Bytes.toString(result.getValue(Bytes.toBytes("book"),Bytes.toBytes("description")));
                String edit =Bytes.toString(result.getValue(Bytes.toBytes("book"),Bytes.toBytes("edit")));
                String nameAuthor =Bytes.toString(result.getValue(Bytes.toBytes("author"),Bytes.toBytes("nameAuthor")));
                int page =Bytes.toInt(result.getValue(Bytes.toBytes("book"),Bytes.toBytes("page")));
                Book book= new Book(key_row,name,page,publish,nameAuthor,description,edit);
                books.add(book);
            }
        } catch (IOException e) {
            e.printStackTrace();

        }finally {
            if(table!=null){
                table.close();
            }
            if (connection!=null){
                connection.close();
            }
        }
        return books;
    }

}
