'''
mark: list of lists
n: number of students

example: 
input:

5
12 45 66 39 78 49
17 98 96 91 94 99
11 31 46 79 82 36
14 67 59 98 95 94
1 35 77 56 95 84

output: 
17, 14, 1, 2
'''

def engineeringCutoff(n, marks):
    
    # initialize res: a list of tuples. 
    # [(id1, total_score1), (id2, total_score2), ... ]
    res = []

    # first add all id's into result list   
    for row in marks:
        # get one student id and this student's scores
        student_id, scores = row[0], row[1:]
        # get total scores for this current student
        total_score = sum(scores)
        # insert tuple into the res list
        res.append((student_id, total_score))

        # remove id from list if any score for this id is less than 35
        for s in scores:
            if s < 35:
                res.remove((student_id, total_score))

    # sort by the total score of the student
    sorted_res = sorted(res, key = lambda x: x[1], reverse=True)

    # zip the tuple to extract the first element aka the id
    id_res = list(zip(*sorted_res))[0]
    
    return id_res

def main():
    n = int(input())
    marks = []
    for i in range(n):
        marks.append(list(map(int, input().split())))
    result = engineeringCutoff(n, marks)
    print(*result)

if __name__=="__main__":
    main()
