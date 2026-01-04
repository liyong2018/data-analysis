#!/usr/bin/env python3
"""
Check specific columns in table_H09
"""

import pymysql

# Database connection info
DB_CONFIG = {
    'host': '192.168.15.203',
    'port': 30314,
    'user': 'root',
    'password': '123456',
    'database': 'evaluate_db',
    'charset': 'utf8mb4'
}

def main():
    try:
        print("Connecting to database...")
        connection = pymysql.connect(**DB_CONFIG)
        print("Connected successfully!\n")

        cursor = connection.cursor()

        # Check table_H09 structure
        print("="*80)
        print("Table H09 columns (last 10 data columns):")
        print("="*80)
        cursor.execute(f"DESCRIBE `table_H09`")
        columns = cursor.fetchall()

        # Filter to show only col_H09* columns
        data_columns = [col for col in columns if col[0].startswith('col_H09')]

        print(f"\nTotal data columns: {len(data_columns)}")
        print("\nLast 10 data columns:")
        for col in data_columns[-10:]:
            print(f"  {col[0]:20s} {col[1]:20s}")

        # Specifically check for col_H09025 and col_H09026
        print("\n" + "="*80)
        print("Checking specific columns mentioned in the issue:")
        print("="*80)

        column_names = [col[0] for col in columns]

        if 'col_H09025' in column_names:
            print("  col_H09025: EXISTS")
        else:
            print("  col_H09025: MISSING")

        if 'col_H09026' in column_names:
            print("  col_H09026: EXISTS")
        else:
            print("  col_H09026: MISSING")

        # Show all column names
        print("\n" + "="*80)
        print("All columns in table_H09:")
        print("="*80)
        for i, col in enumerate(columns, 1):
            print(f"  {i:2d}. {col[0]}")

        cursor.close()
        connection.close()

    except Exception as e:
        print(f"Error: {e}")
        import traceback
        traceback.print_exc()

if __name__ == "__main__":
    main()
