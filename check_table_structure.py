#!/usr/bin/env python3
"""
Check the actual structure of tables to understand what exists.
"""

import pymysql
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

TABLES = [
    'table_B02', 'table_C01', 'table_D01', 'table_E01', 'table_F01',
    'table_G01', 'table_G02', 'table_G03', 'table_G04', 'table_G05',
    'table_G06', 'table_G07', 'table_H01', 'table_H02', 'table_H03',
    'table_H04', 'table_H05', 'table_H06', 'table_H07', 'table_H08',
    'table_H09', 'table_H10', 'table_H11', 'table_I01', 'table_J01'
]

def check_table_structure(connection, table_name):
    """Get column information for a table."""
    cursor = connection.cursor()
    cursor.execute(f"DESCRIBE `{table_name}`")
    columns = cursor.fetchall()
    cursor.close()
    return columns

def main():
    try:
        print("Connecting to database...")
        connection = pymysql.connect(**DB_CONFIG)
        print("Connected successfully!\n")

        # Check first table as sample
        print("="*80)
        print("Sample table structure for table_B02:")
        print("="*80)
        columns = check_table_structure(connection, 'table_B02')
        for col in columns:
            print(f"  {col[0]:20s} {col[1]:20s} {col[2]:10s} {col[3]:10s}")

        print("\n" + "="*80)
        print("Summary of all tables:")
        print("="*80)

        for table_name in TABLES[:5]:  # Show first 5 tables
            columns = check_table_structure(connection, table_name)
            col_names = [col[0] for col in columns]
            print(f"\n{table_name}: {len(columns)} columns")
            print(f"  Columns: {', '.join(col_names[:10])}" +
                  (f" ... and {len(col_names)-10} more" if len(col_names) > 10 else ""))

        connection.close()

    except Exception as e:
        print(f"Error: {e}")
        import traceback
        traceback.print_exc()

if __name__ == "__main__":
    main()
