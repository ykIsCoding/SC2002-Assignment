package Models;
import java.util.EnumSet;

public class PermissionList {

    public enum PermissionType{
    //----------------------ENQUIRY---------
        EDIT_ENQUIRY,  //all
        REPLY_ENQUIRY,  //committee and staff
        SUBMIT_ENQUIRY,  //student
        DELETE_ENQUIRY, //student
        VIEW_ENQUIRY,  //all
        EDIT_ENQUIRY_RESPONSE, // staff and committee
        DELETE_ENQUIRY_RESPONSE, // staff and committee
        CREATE_ENQUIRY_RESPONSE, // staff and committee
    //----------------------SUGGESTIONS----------
        CREATE_SUGGESTION, //committee
        EDIT_SUGGESTION, //committee
        APPROVE_SUGGESTION,  //staff
        SUBMIT_SUGGESTION,  // committee
        DELETE_SUGGESTION, //staff and committee
        VIEW_SUGGESTION,  //staff
    //--------------------REPORT--------------
        CREATE_PERFORMANCE_REPORT,//staff
        CREATE_ATTENDANCE_REPORT,  //staff and committee
        CREATE_STUDENT_ENQUIRY_REPORT, // staff and committee
    }
    private static final EnumSet<PermissionType> staffPermissions = EnumSet.of(
            PermissionType.EDIT_ENQUIRY,
            PermissionType.REPLY_ENQUIRY,
            PermissionType.VIEW_ENQUIRY,
            PermissionType.EDIT_ENQUIRY_RESPONSE,
            PermissionType.DELETE_ENQUIRY_RESPONSE,
            PermissionType.CREATE_ENQUIRY_RESPONSE,
            PermissionType.APPROVE_SUGGESTION,
            PermissionType.DELETE_SUGGESTION,
            PermissionType.VIEW_SUGGESTION,
            PermissionType.CREATE_ATTENDANCE_REPORT,
            PermissionType.CREATE_PERFORMANCE_REPORT,
            PermissionType.CREATE_STUDENT_ENQUIRY_REPORT
        );

    private static final EnumSet<PermissionType> committeePermissions = EnumSet.of(
            PermissionType.EDIT_ENQUIRY,
            PermissionType.REPLY_ENQUIRY,
            PermissionType.VIEW_ENQUIRY,
            PermissionType.EDIT_ENQUIRY_RESPONSE,
            PermissionType.DELETE_ENQUIRY_RESPONSE,
            PermissionType.CREATE_ENQUIRY_RESPONSE,
            PermissionType.CREATE_SUGGESTION,
            PermissionType.EDIT_SUGGESTION,
            PermissionType.SUBMIT_SUGGESTION,
            PermissionType.DELETE_SUGGESTION,
            PermissionType.CREATE_ATTENDANCE_REPORT,
            PermissionType.CREATE_STUDENT_ENQUIRY_REPORT
        );
    private static final EnumSet<PermissionType> studentPermissions = EnumSet.of(
            PermissionType.EDIT_ENQUIRY,
            PermissionType.SUBMIT_ENQUIRY,
            PermissionType.DELETE_ENQUIRY,
            PermissionType.VIEW_ENQUIRY
        );


    public PermissionList(){}

    public static EnumSet<PermissionType> getStaffPermission(){return staffPermissions;}
    public static EnumSet<PermissionType> getStudentPermission(){return studentPermissions;}
    public static EnumSet<PermissionType> getCommitteePermission(){return committeePermissions;}



    
}
