package nssvast.lednss;

import android.provider.BaseColumns;

/*
 * Created by anand on 19-Apr-17.
 */

public final class FeedReaderContract {

    private FeedReaderContract() {}

    public static final String DB_NAME = "ledKerala";
    public static final String UPDATE_STATUS = "update_status";

    public static class FeedEntryHouses implements BaseColumns {
        public static final String TABLE_NAME = "houses";
        public static final String COLUMN_NAME_WARD = "ward";
        public static final String COLUMN_NAME_HOUSE = "house_no";
        public static final String COLUMN_NAME_HEAD = "head";
        public static final String COLUMN_NAME_ADDRESS = "address";
        public static final String COLUMN_NAME_RATION = "ration_no";
        public static final String COLUMN_NAME_CASTE = "caste";
        public static final String COLUMN_NAME_OWN_LAND = "own_land";
        public static final String COLUMN_NAME_LAND = "land";
        public static final String COLUMN_NAME_INCOME = "income";
        public static final String COLUMN_NAME_ELECTRICITY = "electricity_no";
        public static final String COLUMN_NAME_LANDLINE = "landline_no";
        public static final String COLUMN_NAME_GAS = "gas_agency";
        public static final String COLUMN_NAME_WATER = "drinking_water";
        public static final String COLUMN_NAME_LATERINE = "laterine_facility";
        public static final String COLUMN_NAME_VEHICLE_TYPE = "vehicle_type";
        public static final String COLUMN_NAME_LITERACY = "literacy_count";
    }

    public static class FeedEntryMembers implements BaseColumns {
        public static final String TABLE_NAME = "members";
        public static final String COLUMN_NAME_WARD = "ward";
        public static final String COLUMN_NAME_HOUSE = "house_no";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_AGE = "age";
        public static final String COLUMN_NAME_SEX = "sex";
        public static final String COLUMN_NAME_EDU_QUALIFICATIONS = "edu_qualifications";
        public static final String COLUMN_NAME_JOB = "job";
        public static final String COLUMN_NAME_UID = "uid_no";
        public static final String COLUMN_NAME_ELECTION_ID = "election_id";
        public static final String COLUMN_NAME_GOVT_AIDS = "govt_aids";
        public static final String COLUMN_NAME_MOB_NO = "mob_no";
        public static final String COLUMN_NAME_ANY_TRAITS = "any_traits";
    }
}
