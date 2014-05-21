package es.caib.bpm.vo;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import org.jbpm.bytes.ByteArray;

public class Deserializer {
    public static Object deserialize(Object obj)
    {
        if(obj instanceof ByteArray)
        {
            try
            {
                obj =
                new ObjectInputStream(new ByteArrayInputStream(((ByteArray) obj)
                  .getBytes())).readObject();
            }
            catch(final Exception e)
            {
              throw new RuntimeException(e);
            }
        }
        return obj;
    }
}
