#!/usr/bin/env python3
"""
Verify that all database tables have the correct columns matching their entity classes.
"""

import pymysql
import re
from collections import defaultdict

# Database connection info
DB_CONFIG = {
    'host': '192.168.15.203',
    'port': 30314,
    'user': 'root',
    'password': '123456',
    'database': 'evaluate_db',
    'charset': 'utf8mb4'
}

# Entity classes and their BigDecimal fields
ENTITY_FIELDS = {
    'table_B02': [f'colB02{i:03d}' for i in range(1, 74)],
    'table_C01': [f'colC01{i:03d}' for i in range(1, 12)],
    'table_D01': [f'colD01{i:03d}' for i in range(1, 43)],
    'table_E01': [f'colE01{i:03d}' for i in range(1, 16)],
    'table_F01': [f'colF01{i:03d}' for i in range(1, 27)],
    'table_G01': [f'colG01{i:03d}' for i in range(1, 101)],
    'table_G02': [f'colG02{i:03d}' for i in range(1, 13)],
    'table_G03': [f'colG03{i:03d}' for i in range(1, 42)],
    'table_G04': [f'colG04{i:03d}' for i in range(1, 25)],
    'table_G05': [f'colG05{i:03d}' for i in range(1, 43)],
    'table_G06': [f'colG06{i:03d}' for i in range(1, 12)],
    'table_G07': [f'colG07{i:03d}' for i in range(1, 18)],
    'table_H01': [f'colH01{i:03d}' for i in range(1, 16)],
    'table_H02': [f'colH02{i:03d}' for i in range(1, 35)],
    'table_H03': [f'colH03{i:03d}' for i in range(1, 16)],
    'table_H04': [f'colH04{i:03d}' for i in range(1, 19)],
    'table_H05': [f'colH05{i:03d}' for i in range(1, 15)],
    'table_H06': [f'colH06{i:03d}' for i in range(1, 11)],
    'table_H07': [f'colH07{i:03d}' for i in range(1, 24)],
    'table_H08': [f'colH08{i:03d}' for i in range(1, 38)],
    'table_H09': [f'colH09{i:03d}' for i in range(1, 28)],
    'table_H10': [f'colH10{i:03d}' for i in range(1, 13)],
    'table_H11': [f'colH11{i:03d}' for i in range(1, 13)],
    'table_I01': [f'colI01{i:03d}' for i in range(1, 12)],
    'table_J01': [f'colJ01{i:03d}' for i in range(1, 109)],
}

def get_table_columns(connection, table_name):
    """Get all column names for a table."""
    cursor = connection.cursor()
    cursor.execute(f"SHOW COLUMNS FROM `{table_name}`")
    columns = [row[0] for row in cursor.fetchall()]
    cursor.close()
    return columns

def main():
    try:
        print("Connecting to database...")
        connection = pymysql.connect(**DB_CONFIG)
        print("Connected successfully!\n")

        mismatches = defaultdict(list)
        all_tables_valid = True

        for table_name, expected_fields in ENTITY_FIELDS.items():
            print(f"Checking {table_name}...")

            # Get actual columns from database
            actual_columns = get_table_columns(connection, table_name)

            # Check for missing columns
            missing = []
            for field in expected_fields:
                if field not in actual_columns:
                    missing.append(field)
                    all_tables_valid = False

            if missing:
                mismatches[table_name] = missing
                print(f"  MISSING {len(missing)} columns: {', '.join(missing[:5])}" +
                      (f" ... and {len(missing)-5} more" if len(missing) > 5 else ""))
            else:
                print(f"  All {len(expected_fields)} columns present!")

        print("\n" + "="*80)
        if all_tables_valid:
            print("SUCCESS: All tables have the correct columns!")
        else:
            print(f"FOUND ISSUES: {len(mismatches)} table(s) have missing columns\n")
            print("="*80)

            # Generate ALTER TABLE statements
            print("\nGenerating SQL fix script...")
            with open('d:/Evaluation/data-analysis/fix_missing_columns.sql', 'w') as f:
                f.write("-- Fix script for missing database columns\n")
                f.write("-- Generated automatically\n")
                f.write("-- Execute this script to add all missing columns\n\n")

                for table_name, missing_cols in mismatches.items():
                    f.write(f"-- Table: {table_name}\n")
                    for col in missing_cols:
                        f.write(f"ALTER TABLE `{table_name}` ADD COLUMN `{col}` DECIMAL(20,4) DEFAULT NULL COMMENT 'Data column';\n")
                    f.write("\n")

            print("SQL fix script saved to: fix_missing_columns.sql")
            print("\nMissing columns summary:")
            for table_name, missing_cols in mismatches.items():
                print(f"  {table_name}: {len(missing_cols)} missing columns")

        connection.close()

    except Exception as e:
        print(f"Error: {e}")
        import traceback
        traceback.print_exc()

if __name__ == "__main__":
    main()
