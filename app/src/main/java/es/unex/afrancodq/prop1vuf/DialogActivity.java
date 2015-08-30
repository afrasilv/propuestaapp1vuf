package es.unex.afrancodq.prop1vuf;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by alex on 17/06/15.
 */
public class DialogActivity extends AppCompatActivity {

    boolean sesionIniciada;
    String[] arrayValuesHideAnim = new String[] {"Cola", "Lista", "Lista Doblemente Enlazada"};
    String[] arraySubjectsTeacher = new String[] {"Introducción a la Programación", "Estructura de Datos y de la Información", "DMSS", "PI", "DP", "ASEE"};
    String[] arrayIPAnims = new String[] {"Módulo", "Punteros"};
    String[] arrayEDIAnims = new String[] {"Cola", "Pila", "Lista", "Lista Doblemente Enlazada", "Ficheros"};
    String[] topicsEDI = new String[] {"Tema 1: Programación orientada a objetos", "Tema 2: Análisis y diseño de sistemas orientado a objetos",
            "Tema 3: Estructuras de datos lineales", "Tema 4: Estructuras de almacenamiento secundario", "Tema 5: Estructuras de datos no lineales"};
    String[] topicsIP = new String[] {"Tema 1: Introducción", "Tema 2: Primeros algoritmos",
            "Tema 3: Programación modular", "Tema 4: Programación Estructurada", "Tema 5: Recursividad",
            "Tema 6: Análisis de algoritmos", "Tema 7: TAD y estructuras de datos",
            "Tema 8: Registros", "Tema 9: Vectores", "Tema 10: Gestión dinámica de memoria"};
    int selectSubject;
    int selectTopic;
    final ArrayList<String> listValuesHideAnim = new ArrayList<String>();
    final ArrayList<String> listValuesTeacherSubjects = new ArrayList<String>();
    ArrayList<String> listValuesAnimsSubject = new ArrayList<String>();
    ArrayList<String> listValuesTopicsSubject = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sesionIniciada = getIntent().getBooleanExtra("sesionIniciada", false);

        if(sesionIniciada == true)
            sesionIniciada = true;
        else {
            setContentView(R.layout.dialog_activity);
            optionsWithoutLogin();
        }
    }

    /**
     * Control all buttons to the dialog without login
     */
    public void optionsWithoutLogin(){
        Button buttonInicSes = (Button) findViewById(R.id.initSession);
        Button register = (Button) findViewById(R.id.register);
        Button changeSub = (Button) findViewById(R.id.changeSubject);
        Button settings = (Button) findViewById(R.id.settings);

        buttonInicSes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                setContentView(R.layout.dialog_login);
                login();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                setContentView(R.layout.dialog_register);
                register();
            }
        });


        settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                setContentView(R.layout.dialog_noacc_settings);
                settingsNoAccount();
            }
        });

    }

    /**
     * Control login layout to the dialog
     */
    public void login(){
        Button backInit = (Button) findViewById(R.id.backToInit);
        Button login = (Button) findViewById(R.id.login);

        backInit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                setContentView(R.layout.dialog_activity);
                optionsWithoutLogin();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                setContentView(R.layout.teacher_account);
                teacherAccount();
            }
        });
    }

    /**
     * Control register layout to the dialog
     */
    public void register(){
        Button backInit = (Button) findViewById(R.id.backToInit);
        Button register = (Button) findViewById(R.id.register);

        backInit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                setContentView(R.layout.dialog_activity);
                optionsWithoutLogin();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Toast.makeText(DialogActivity.this, "Te has registrado con éxito", Toast.LENGTH_SHORT).show();
                setContentView(R.layout.dialog_activity);
                optionsWithoutLogin();
            }
        });
    }

    public void teacherAccount(){
        Button mySubjects = (Button) findViewById(R.id.mySubjects);
        Button liveAnims = (Button) findViewById(R.id.liveAnim);
        Button editGraph = (Button) findViewById(R.id.editGraph);
        Button settingsTeach = (Button) findViewById(R.id.settingsTeacher);

        if(listValuesTeacherSubjects.isEmpty()) {
            for (int i = 0; i < arraySubjectsTeacher.length; i++) {
                listValuesTeacherSubjects.add(arraySubjectsTeacher[i]);
            }
        }

        mySubjects.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                setContentView(R.layout.teacher_subjects);
                ListView lv = (ListView) findViewById(R.id.listTeacherSubjects);
                final StableArrayAdapter adapter = new StableArrayAdapter(getApplicationContext(), listValuesTeacherSubjects, 1);
                lv.setAdapter(adapter);

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, final View view,
                                            int position, long id) {
                        final String item = (String) parent.getItemAtPosition(position);
                        Toast.makeText(DialogActivity.this, "Has seleccionado la asignatura "+item, Toast.LENGTH_SHORT).show();
                        selectSubject = position;
                        setContentView(R.layout.topics_subject_list);
                        topicsSubject();
                    }

                });


                teacherSubjects();
            }
        });

        liveAnims.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                setContentView(R.layout.livear_options);
                liveArOptions();
            }
        });

        settingsTeach.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                setContentView(R.layout.dialog_account_settings);
                settingsAccount();
            }
        });
    }

    public void liveArOptions(){
        Button backToAccount = (Button) findViewById(R.id.backToAccount);
        Button addNewAnim = (Button) findViewById(R.id.addNewAnim);
        Button ActHideAnim = (Button) findViewById(R.id.ActivateHideAnim);

        backToAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                setContentView(R.layout.teacher_account);
                teacherAccount();
            }
        });

        addNewAnim.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                setContentView(R.layout.public_anim_live);
                Spinner spinner = (Spinner) findViewById(R.id.spinner_time);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.live_time_array, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);

                publicNewAnim();
            }
        });

        if(listValuesHideAnim.isEmpty()) {
            for (int i = 0; i < arrayValuesHideAnim.length; i++) {
                listValuesHideAnim.add(arrayValuesHideAnim[i]);
            }
        }

        ActHideAnim.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                setContentView(R.layout.act_hidden_anim);
                ListView lv = (ListView) findViewById(R.id.listHideAnim);
                final StableArrayAdapter adapter = new StableArrayAdapter(getApplicationContext(), listValuesHideAnim, 0);
                lv.setAdapter(adapter);

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, final View view,
                                            int position, long id) {
                        final String item = (String) parent.getItemAtPosition(position);
                        Toast.makeText(DialogActivity.this, "Has activado la animación "+item, Toast.LENGTH_SHORT).show();
                    }

                });


                actHiddenAnim();
            }
        });
    }

    public void publicNewAnim(){
        Button backToLiveArOptions = (Button) findViewById(R.id.backToLiveAr);
        Button selectFile = (Button) findViewById(R.id.selectFile);
        Button addAnim = (Button) findViewById(R.id.add_anim);

        backToLiveArOptions.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                setContentView(R.layout.livear_options);
                liveArOptions();
            }
        });

        selectFile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Toast.makeText(DialogActivity.this, "Seleccione un fichero... Próximamente", Toast.LENGTH_SHORT).show();
            }
        });

        addAnim.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Toast.makeText(DialogActivity.this, "Animación añadida", Toast.LENGTH_SHORT).show();
                setContentView(R.layout.livear_options);
                liveArOptions();
            }
        });
    }

    public void actHiddenAnim(){
        Button backToLiveArOptions = (Button) findViewById(R.id.backToLiveAr);

        backToLiveArOptions.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                setContentView(R.layout.livear_options);
                liveArOptions();
            }
        });
    }

    public void teacherSubjects(){
        Button backToAccount = (Button) findViewById(R.id.backToAccount);
        Button settingsSubject = (Button) findViewById(R.id.settingsSubject);


        backToAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                setContentView(R.layout.teacher_account);
                teacherAccount();
            }
        });

        settingsSubject.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                setContentView(R.layout.dialog_subject_settings);
                subjectSettings();
            }
        });
    }

    public void topicsSubject(){
        Button backToMySubjects = (Button) findViewById(R.id.backToSubject);
        listValuesTopicsSubject.clear();

        TextView tv = (TextView) findViewById(R.id.nameSubject);
        tv.setText(arraySubjectsTeacher[selectSubject]);

        backToMySubjects.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                setContentView(R.layout.teacher_subjects);
                ListView lv = (ListView) findViewById(R.id.listTeacherSubjects);
                final StableArrayAdapter adapter = new StableArrayAdapter(getApplicationContext(), listValuesTeacherSubjects, 1);
                lv.setAdapter(adapter);

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, final View view,
                                            int position, long id) {
                        final String item = (String) parent.getItemAtPosition(position);
                        Toast.makeText(DialogActivity.this, "Has seleccionado la asignatura "+item, Toast.LENGTH_SHORT).show();
                        selectSubject = position;
                        setContentView(R.layout.topics_subject_list);
                        topicsSubject();
                    }

                });


                teacherSubjects();
            }
        });

        if(selectSubject==0) {
            for (int i = 0; i < topicsIP.length; i++) {
                listValuesTopicsSubject.add(topicsIP[i]);
            }
        }
        else if(selectSubject==1) {
            for (int i = 0; i < topicsEDI.length; i++) {
                listValuesTopicsSubject.add(topicsEDI[i]);
            }

        }

        ListView lv = (ListView) findViewById(R.id.listTopicsSubjects);
        final StableArrayAdapter adapter = new StableArrayAdapter(getApplicationContext(), listValuesTopicsSubject, 1);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                Toast.makeText(DialogActivity.this, "Has seleccionado el tema "+item, Toast.LENGTH_SHORT).show();
                selectTopic = position;
                setContentView(R.layout.dialog_subject);
                subjectTeacherMenu();
            }

        });

    }


    public void subjectTeacherMenu(){

        TextView tv = (TextView) findViewById(R.id.nameSubject);
        tv.setText(arraySubjectsTeacher[selectSubject]);

        final TextView tvTopic = (TextView) findViewById(R.id.nameTopic);
        switch (selectSubject){
            case 0:
                tvTopic.setText(topicsIP[selectTopic]);
                break;
            case 1:
                tvTopic.setText(topicsEDI[selectTopic]);
                break;
        }

        Button backToMyTopics = (Button) findViewById(R.id.backToTopicsSubjects);
        Button actHideAnimSubject = (Button) findViewById(R.id.actHideAnim);
        Button addAnimSubject = (Button) findViewById(R.id.addAnim);
        Button editAnim = (Button) findViewById(R.id.editAnim);
        Button settingsTopic = (Button) findViewById(R.id.settingsTopic);

        if(listValuesTeacherSubjects.isEmpty()) {
            for (int i = 0; i < arraySubjectsTeacher.length; i++) {
                listValuesTeacherSubjects.add(arraySubjectsTeacher[i]);
            }
        }

        backToMyTopics.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                setContentView(R.layout.topics_subject_list);
                topicsSubject();
            }
        });

        listValuesAnimsSubject.clear();
        String[] temp;
        if(selectSubject == 0)
            temp = arrayIPAnims;
        else
            temp = arrayEDIAnims;
        for (int i = 0; i < temp.length; i++) {
            listValuesAnimsSubject.add(temp[i]);
        }


        actHideAnimSubject.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                setContentView(R.layout.act_hidden_anim);
                ListView lv = (ListView) findViewById(R.id.listHideAnim);
                final StableArrayAdapter adapter = new StableArrayAdapter(getApplicationContext(), listValuesAnimsSubject, 0);
                lv.setAdapter(adapter);

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, final View view,
                                            int position, long id) {
                        final String item = (String) parent.getItemAtPosition(position);
                        Toast.makeText(DialogActivity.this, "Has activado la animación "+item, Toast.LENGTH_SHORT).show();
                    }

                });


                actHiddenAnimFromSubject();
            }
        });

        addAnimSubject.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                setContentView(R.layout.public_anim_live);
                Spinner spinner = (Spinner) findViewById(R.id.spinner_time);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.live_time_array, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);

                publicNewAnimFromSubject();
            }
        });

        settingsTopic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                setContentView(R.layout.dialog_topic_settings);
                Button bt = (Button) findViewById(R.id.backToSubject);
                bt.setText(tvTopic.getText());
                topicSettings();
            }
        });
    }

    public void actHiddenAnimFromSubject(){
        Button backToLiveArOptions = (Button) findViewById(R.id.backToLiveAr);

        backToLiveArOptions.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                setContentView(R.layout.dialog_subject);
                subjectTeacherMenu();
            }
        });
    }

    public void publicNewAnimFromSubject(){
        Button backToLiveArOptions = (Button) findViewById(R.id.backToLiveAr);
        Button selectFile = (Button) findViewById(R.id.selectFile);
        Button addAnim = (Button) findViewById(R.id.add_anim);

        backToLiveArOptions.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                setContentView(R.layout.dialog_subject);
                subjectTeacherMenu();
            }
        });

        selectFile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Toast.makeText(DialogActivity.this, "Seleccione un fichero... Próximamente", Toast.LENGTH_SHORT).show();
            }
        });

        addAnim.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Toast.makeText(DialogActivity.this, "Animación añadida", Toast.LENGTH_SHORT).show();
                setContentView(R.layout.dialog_subject);
                subjectTeacherMenu();
            }
        });
    }

    public void settingsAccount(){
        Button backToAccount = (Button) findViewById(R.id.backToAccount);
        Button editDataAccount = (Button) findViewById(R.id.editDataAccount);
        Button about = (Button) findViewById(R.id.about);
        Button closeSession = (Button) findViewById(R.id.closeSession);

        backToAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                setContentView(R.layout.teacher_account);
                teacherAccount();
            }
        });

        editDataAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                setContentView(R.layout.edit_account_data);
                editData();
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                new MaterialDialog.Builder(DialogActivity.this)
                    .title(R.string.app_name)
                        .content(R.string.content)
                        .positiveText("Aceptar")
                        .show();
            }
        });

        closeSession.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                setContentView(R.layout.dialog_activity);
                sesionIniciada = false;
                optionsWithoutLogin();
            }
        });


    }

    public void editData(){
        Button backToSettings = (Button) findViewById(R.id.backToSettings);
        Button editData = (Button) findViewById(R.id.editAccountData);
        Button removeAccount = (Button) findViewById(R.id.deleteAccount);

        backToSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                setContentView(R.layout.dialog_account_settings);
                settingsAccount();
            }
        });

        editData.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Toast.makeText(DialogActivity.this, "Se han modificado los datos de su cuenta", Toast.LENGTH_SHORT).show();
                setContentView(R.layout.dialog_account_settings);
                settingsAccount();
            }
        });

        removeAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                final MaterialDialog dialogDeleteAccount = new MaterialDialog.Builder(DialogActivity.this)
                        .title("Eliminar cuenta")
                        .content(R.string.deleteAccount)
                        .positiveText("Cancelar")
                        .negativeText("Eliminar Cuenta")
                        .negativeColorRes(R.color.material_red_500)
                        .show();

                View negative = dialogDeleteAccount.getActionButton(DialogAction.NEGATIVE);
                negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogDeleteAccount.dismiss();

                        Toast.makeText(DialogActivity.this, "Has eliminado su cuenta. Esperamos volver a contar con usted.", Toast.LENGTH_SHORT).show();

                        setContentView(R.layout.dialog_activity);
                        optionsWithoutLogin();
                    }
                });
            }
        });
    }

    public void settingsNoAccount(){
        Button backToAccount = (Button) findViewById(R.id.backToInit);
        Button about = (Button) findViewById(R.id.about);

        backToAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                setContentView(R.layout.dialog_activity);
                optionsWithoutLogin();
            }
        });


        about.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                new MaterialDialog.Builder(DialogActivity.this)
                        .title(R.string.app_name)
                        .content(R.string.content)
                        .positiveText("Aceptar")
                        .show();
            }
        });

    }

    public void subjectSettings(){
        Button backToSubjects = (Button) findViewById(R.id.backToSubjectList);
        Button deleteSubject = (Button) findViewById(R.id.deleteSubject);

        backToSubjects.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                setContentView(R.layout.teacher_subjects);
                ListView lv = (ListView) findViewById(R.id.listTeacherSubjects);
                final StableArrayAdapter adapter = new StableArrayAdapter(getApplicationContext(), listValuesTeacherSubjects, 1);
                lv.setAdapter(adapter);

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, final View view,
                                            int position, long id) {
                        final String item = (String) parent.getItemAtPosition(position);
                        Toast.makeText(DialogActivity.this, "Has seleccionado la asignatura "+item, Toast.LENGTH_SHORT).show();
                        selectSubject = position;
                        setContentView(R.layout.topics_subject_list);
                        topicsSubject();
                    }

                });


                teacherSubjects();
            }
        });

        deleteSubject.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                final MaterialDialog dialogSubject = new MaterialDialog.Builder(DialogActivity.this)
                        .title(R.string.app_name)
                        .content(R.string.deleteSubject)
                        .positiveText("Cancelar")
                        .negativeText("Eliminar Asignatura")
                        .negativeColorRes(R.color.material_red_500)
                        .show();

                View negative = dialogSubject.getActionButton(DialogAction.NEGATIVE);
                negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogSubject.dismiss();
                        setContentView(R.layout.teacher_subjects_delete);
                        ListView lv = (ListView) findViewById(R.id.listTeacherSubjects);
                        final StableArrayAdapter adapter = new StableArrayAdapter(getApplicationContext(), listValuesTeacherSubjects, 1);
                        lv.setAdapter(adapter);

                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                            @Override
                            public void onItemClick(AdapterView<?> parent, final View view,
                                                    int position, long id) {
                                final String item = (String) parent.getItemAtPosition(position);

                                final MaterialDialog dialogDeleteSubject = new MaterialDialog.Builder(DialogActivity.this)
                                        .title(item)
                                        .content(R.string.deleteFinalSubject)
                                        .positiveText("Cancelar")
                                        .negativeText("Eliminar Asignatura")
                                        .negativeColorRes(R.color.material_red_500)
                                        .show();

                                View negative = dialogDeleteSubject.getActionButton(DialogAction.NEGATIVE);
                                negative.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialogDeleteSubject.dismiss();

                                        Toast.makeText(DialogActivity.this, "Has eliminado la asignatura "+item, Toast.LENGTH_SHORT).show();


                                        setContentView(R.layout.teacher_subjects);
                                        ListView lv = (ListView) findViewById(R.id.listTeacherSubjects);
                                        final StableArrayAdapter adapter = new StableArrayAdapter(getApplicationContext(), listValuesTeacherSubjects, 1);
                                        lv.setAdapter(adapter);

                                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                            @Override
                                            public void onItemClick(AdapterView<?> parent, final View view,
                                                                    int position, long id) {
                                                final String item = (String) parent.getItemAtPosition(position);
                                                Toast.makeText(DialogActivity.this, "Has seleccionado la asignatura "+item, Toast.LENGTH_SHORT).show();
                                                selectSubject = position;
                                                setContentView(R.layout.topics_subject_list);
                                                topicsSubject();
                                            }

                                        });


                                        teacherSubjects();
                                    }
                                });
                            }

                        });


                        deleteSubjects();

                    }
                });
            }
        });
    }

    public void deleteSubjects(){
        Button backToSubjects = (Button) findViewById(R.id.backToSubjects);

        backToSubjects.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                setContentView(R.layout.teacher_subjects);
                ListView lv = (ListView) findViewById(R.id.listTeacherSubjects);
                final StableArrayAdapter adapter = new StableArrayAdapter(getApplicationContext(), listValuesTeacherSubjects, 1);
                lv.setAdapter(adapter);

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, final View view,
                                            int position, long id) {
                        final String item = (String) parent.getItemAtPosition(position);
                        Toast.makeText(DialogActivity.this, "Has seleccionado la asignatura "+item, Toast.LENGTH_SHORT).show();
                        selectSubject = position;
                        setContentView(R.layout.topics_subject_list);
                        topicsSubject();
                    }

                });


                teacherSubjects();
            }
        });
    }


    public void topicSettings(){
        Button backToSubject = (Button) findViewById(R.id.backToSubject);
        Button deleteTopic = (Button) findViewById(R.id.deleteTopic);

        backToSubject.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                setContentView(R.layout.dialog_subject);
                subjectTeacherMenu();
            }
        });

        deleteTopic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                final MaterialDialog dialogTopic = new MaterialDialog.Builder(DialogActivity.this)
                        .title(R.string.app_name)
                        .content(R.string.deleteTopic)
                        .positiveText("Cancelar")
                        .negativeText("Eliminar Tema")
                        .negativeColorRes(R.color.material_red_500)
                        .show();

                View negative = dialogTopic.getActionButton(DialogAction.NEGATIVE);
                negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogTopic.dismiss();
                        setContentView(R.layout.topics_subject_list);
                        topicsSubject();
                    }
                });
            }
        });
    }


    private class StableArrayAdapter extends ArrayAdapter<String> {

        private final Context context;
        private final List<String> listValues;
        private int optionSelect;


        public StableArrayAdapter(Context context, List<String> objects, int option) {
            super(context, -1, objects);
            this.context = context;
            this.listValues = objects;
            this.optionSelect = option;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if(optionSelect == 0) {
                View rowView = inflater.inflate(R.layout.item_hide_anim, parent, false);
                TextView tv = (TextView) rowView.findViewById(R.id.rowText);
                tv.setText(listValues.get(position));
                return rowView;
            }
            else{
                View rowView = inflater.inflate(R.layout.card_subject, parent, false);
                TextView tv = (TextView) rowView.findViewById(R.id.subjectName);
                tv.setText(listValues.get(position));
                return rowView;
            }
        }

    }
}
