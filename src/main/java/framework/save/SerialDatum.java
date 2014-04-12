package framework.save;

import inspire.Datum;

/**
 * Created by Lex on 12/04/2014.
 */
public class SerialDatum<T> extends Datum {

    public SerialDatum(java.lang.String key, T value){
        super(key, value);
    }

    @Override
    public String toString(){
        return this.getValue().getClass().getCanonicalName() + "|" + String.valueOf(this.getValue());
    }

    public void fromString(String value){
        String[] data = value.split("|");
        if(data[0].equals(String.class.getCanonicalName()))
            this.setValue((String) data[1]);
        else if(data[0].equals(Integer.class.getCanonicalName()))
            this.setValue(Integer.valueOf(data[1]));
        else if(data[0].equals(Double.class.getCanonicalName()))
            this.setValue(Double.valueOf(data[1]));
        else if(data[0].equals(Boolean.class.getCanonicalName()))
            this.setValue(Boolean.valueOf(data[1]));
    }
}
