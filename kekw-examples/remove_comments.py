def remove_comments(file_path):
    with open(file_path, 'r+') as f:
        lines = f.readlines()
        f.seek(0)

        for line in lines:
            formatted_line = line.split('//')[0]
            if '\n' in formatted_line:
                f.write(formatted_line)
            else:
                f.write(formatted_line + '\n')

        f.truncate()


def main(argv):
    if len(sys.argv) != 2:
        print(f'Usage {sys.argv[0]} file_path')
        exit(1)
    file_path = sys.argv[1]
    remove_comments(file_path)


if __name__ == '__main__':
    import sys

    main(sys.argv)
