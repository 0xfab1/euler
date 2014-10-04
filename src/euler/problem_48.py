N = 10**10
print str(sum(pow(x, x, N) for x in xrange(1, 11)))[-10:]