/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cortopoofinal;

import dao.InscripcionesDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Inscripciones;

/**
 *
 * @author Erick <00092217@uca.edu.sv>
 */
public class CortoPOOFinal extends JFrame{

    /**
     * @param args the command line arguments
     */
    public JTextField carnet,nombre,apellidos,edad;
    public JLabel lblCarnet,lblNombre,lblApellidos,lblEdad,lblUniversidad,lblEstado;
    public JButton buscar,insertar,actualizar,eliminar,limpiar;
    public JComboBox universidades;
    public JRadioButton si,no;
    public JTable resultados;
    public JPanel table;
    ButtonGroup estado = new ButtonGroup();
    
    private static final int ANCHOC = 130, ALTOC = 30;
    
    DefaultTableModel tm;
    
    public CortoPOOFinal(){
        super("Inscripciones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        formulario();
        llenarTabla();
        agregarLabels();
       Container container = getContentPane();
        container.add(carnet);
       container.add(nombre);
        container.add(apellidos);
        container.add(edad);
        container.add(lblCarnet);
        container.add(lblNombre);
        container.add(lblApellidos);
        container.add(lblEdad);
        container.add(lblUniversidad);
        container.add(lblEstado);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(universidades);
        container.add(si);
        container.add(no);
        container.add(table);
        
        setSize(1000,800);
        eventos();
    }
    public final void agregarLabels(){
        lblCarnet = new JLabel("Codigo");
        lblNombre = new JLabel("Nombre");
        lblApellidos = new JLabel("Apellidos");
        lblEdad = new JLabel("Edad");
        lblUniversidad = new JLabel("Universidad");
        lblEstado = new JLabel("Estado");
        lblCarnet.setBounds(10,25,ANCHOC,ALTOC);
        lblNombre.setBounds(10,70,ANCHOC,ALTOC);
        lblApellidos.setBounds(300,70,ANCHOC,ALTOC);
        lblEdad.setBounds(10,120,ANCHOC,ALTOC);
        lblUniversidad.setBounds(10,170,ANCHOC,ALTOC);
        lblEstado.setBounds(10,200,ANCHOC,ALTOC);
    }
    public final void formulario(){
        carnet = new JTextField();
        nombre = new JTextField();
        apellidos = new JTextField();
        edad = new JTextField();
        
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        limpiar = new JButton("Limpiar");
        
        universidades = new JComboBox();
        si = new JRadioButton("si",true);
        no = new JRadioButton("no");
        resultados = new JTable();
        
        table = new JPanel();
        
        universidades.addItem("UCA");
        universidades.addItem("UDB");
        universidades.addItem("UFG");
        universidades.addItem("UGB");
        
        estado = new ButtonGroup();
        estado.add(si);
        estado.add(no);
        
        carnet.setBounds(45,25,ANCHOC,ALTOC);
        nombre.setBounds(45,70,ANCHOC,ALTOC);
        apellidos.setBounds(400,70,ANCHOC,ALTOC);
        edad.setBounds(45,115,ANCHOC,ALTOC);
        universidades.setBounds(200,160,ANCHOC,ALTOC);
        si.setBounds(45,200,ANCHOC,ALTOC);
        no.setBounds(200,200,ANCHOC,ALTOC);
        
        buscar.setBounds(250,25,ANCHOC,ALTOC);
        insertar.setBounds(45,250,ANCHOC,ALTOC);
        eliminar.setBounds(245,250,ANCHOC,ALTOC);
        actualizar.setBounds(445,250,ANCHOC,ALTOC);
        limpiar.setBounds(645,250,ANCHOC,ALTOC);
        
        
        
        resultados = new JTable();
        table.setBounds(10,350,500,200);
        table.add(new JScrollPane(resultados));
    }
    public void llenarTabla(){
        tm = new DefaultTableModel(){
            public Class<?> getColumnClass(int column){
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };
        tm.addColumn("Carnet");
        tm.addColumn("Nombres");
        tm.addColumn("apellidos");
        tm.addColumn("Universidad");
        tm.addColumn("Estado");
        
        InscripcionesDao fd = new InscripcionesDao();
        ArrayList<Inscripciones> filtros = fd.readAll();
        
        //Agregamos el resultado a nuestro JTable
        //se agregan de tipo Object
        boolean b=true;
        for (Inscripciones fi: filtros){
            if(fi.getEstado()==1){
                b=true;
            }else{
                b=false;
            }
            tm.addRow(new Object[]{fi.getCarnet(),fi.getNombres(),fi.getApellidos(),fi.getUniversidad(),b});
            
        }
        //le agregamos el modelo a nuestra tabla
        resultados.setModel(tm);
    }
    public void eventos(){
        //insertar
        insertar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                InscripcionesDao fd = new InscripcionesDao();
                int radioB = 0;
                if(si.isSelected()){
                    radioB=1;
                }if(no.isSelected()){
                    radioB=0;
                }
                Inscripciones f = new Inscripciones(carnet.getText(),nombre.getText(),apellidos.getText(),Integer.parseInt(edad.getText()),universidades.getSelectedItem().toString(),radioB);
                
                if(fd.create(f)){
                    JOptionPane.showMessageDialog(null, "Filtro registrado con exito");
                    limpiarCampos();
                    llenarTabla();
                }else{
                    JOptionPane.showMessageDialog(null,"Ocurrio un problema al momento de crear el filtro");
                }
            }
            
        });
        
        actualizar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int radioB = 0;
                if(si.isSelected()){
                    radioB=1;
                }if(no.isSelected()){
                    radioB=0;
                }
                InscripcionesDao fd = new InscripcionesDao();
                Inscripciones f = new Inscripciones(carnet.getText(),nombre.getText(),apellidos.getText(),Integer.parseInt(edad.getText()),universidades.getSelectedItem().toString(),radioB);
                
                if(fd.update(f)){
                    JOptionPane.showMessageDialog(null, "Filtro registrado con exito");
                    limpiarCampos();
                    llenarTabla();
                }else{
                    JOptionPane.showMessageDialog(null,"Ocurrio un problema al momento de crear el filtro");
                }
            }
            
        });
        
        eliminar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                InscripcionesDao fd = new InscripcionesDao();
                if (fd.delete(carnet.getText())){
                    JOptionPane.showMessageDialog(null,"Filtro Eliminado con exito");
                    limpiarCampos();
                    llenarTabla();
                }else{
                    JOptionPane.showMessageDialog(null,"Ocurrio un problema al momento de crear el filtro");
                }
            }
        });
        
        buscar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                InscripcionesDao fd = new InscripcionesDao();
                Inscripciones f = fd.read(carnet.getText());
                if(f==null){
                    JOptionPane.showMessageDialog(null, "Filtro buscado no se ha encontrado");
                    
                }else{
                   carnet.setText(f.getCarnet());
                   nombre.setText(f.getNombres());
                   apellidos.setText(f.getApellidos());
                   universidades.setSelectedItem(f.getUniversidad());
                   edad.setText(Integer.toString(f.getEdad()));
                   if (f.getEstado()==1){
                       si.setSelected(true);
                   }else{
                       no.setSelected(true);
                   }
                }
            }
            
        });
        
        limpiar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    }
    public void limpiarCampos(){
        carnet.setText("");
        nombre.setText("");
        apellidos.setText("");
        universidades.setSelectedItem("UCA");
        edad.setText("");
    }
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                new CortoPOOFinal().setVisible(true);
            }
            
        });
    }
}

